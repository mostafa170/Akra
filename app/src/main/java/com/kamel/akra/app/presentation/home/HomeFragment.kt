package com.kamel.akra.app.presentation.home

import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.kamel.akra.R
import com.kamel.akra.app.presentation.main.MainActivityEventsListener
import com.kamel.akra.app.presentation.prayer.AzanNotification
import com.kamel.akra.app.presentation.prayer.AzanNotificationData
import com.kamel.akra.data.utils.*
import com.kamel.akra.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(context) {
            "Context must not be null"
        }
        (context as ContextWrapper).baseContext as MainActivityEventsListener
    }

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access LocationServices after onActivityCreated()"
        }
        LocationServices.getFusedLocationProviderClient(activity)
    }

    private lateinit var locationManager: MyLocationManager
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val currentLocation = locationResult.locations.first { it != null }
            if (currentLocation != null) {
                viewModel.downloadPrayers(currentLocation,0)
                locationManager.stopLocationUpdates()
            }
            // TODO can't get location
            // TODO can't get location
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        locationManager = MyLocationManager(fusedLocationClient, locationCallback,requireContext())
        if (!MyPermissions.isPermissionsGranted(this.context, locationPermissions))
            MyPermissions.requestPermissionFragment(
                this,
                locationPermissions,
                LOCATION_PERMISSIONS_REQUEST_CODE
            )
        else
            locationManager.startLocationUpdates()


        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }

        viewModel.goToScreen.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it) {
                    1 -> findNavController().navigate(HomeFragmentDirections.actionToTilawaFragment())
                    2 -> findNavController().navigate(HomeFragmentDirections.actionToQiblaFragment())
                    3 -> findNavController().navigate(HomeFragmentDirections.actionToSebhaFragment())
                    4 -> findNavController().navigate(HomeFragmentDirections.actionToRadioFragment())
                    5 -> findNavController().navigate(HomeFragmentDirections.actionToQuranFragment())
                    6 -> findNavController().navigate(HomeFragmentDirections.actionToAzkarFragment())
                    7 -> findNavController().navigate(HomeFragmentDirections.actionToHadethFragment())
                    8 -> findNavController().navigate(HomeFragmentDirections.actionToPrayersFragment())
                }
                viewModel.restScreen()
            }
        }

        viewModel.upcomingPrayer.observe(viewLifecycleOwner) {
            if (it != null) {
                viewModel.setUpUpcomingPrayer(it)
                AzanNotification.scheduleNotification(
                    requireContext(), AzanNotificationData(
                        it.id,
                        it.id.toPrayerName(resources),
                        resources.getString(
                            R.string.prayer_time_notification_come,
                            it.id.toPrayerName(resources, showAthan = false)
                        ),
                        it.dateTime
                    )
                )
            }
        }
        viewModel.shouldGetNextUpcomingPrayer.observe(viewLifecycleOwner) {
            if (it != null && it) {
                viewModel.upcomingPrayer.observe(viewLifecycleOwner) { prayer ->
                    if (prayer != null)
                        viewModel.setUpUpcomingPrayer(prayer)
                }
                viewModel.loadedNextUpcomingPrayer()
            }
        }

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                locationManager.startLocationUpdates()
        }
    }
}