package com.kamel.akra.app.presentation.prayer

import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.location.Location
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
import com.kamel.akra.app.utilsView.MyDialog
import com.kamel.akra.data.utils.*
import com.kamel.akra.databinding.FragmentPrayersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrayersFragment : Fragment() {

    private val viewModel: PrayersViewModel by viewModels()

    private val mainActivityEventsListener: MainActivityEventsListener by lazy {
        requireNotNull(context) {
            "Context must not be null"
        }
        (context as ContextWrapper).baseContext as MainActivityEventsListener
    }

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access LocationServices after onActivityCreated()"
        }
        LocationServices.getFusedLocationProviderClient(activity)
    }

    private val myDialog: MyDialog by lazy {
        val activity = requireNotNull(this.activity) {
            "Can't create without activity"
        }
        MyDialog(activity)
    }

    private var currentLocation: Location? = null
    private lateinit var locationManager: MyLocationManager
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            if(locationResult != null) {
                currentLocation = locationResult.locations.first { it != null }
                if (currentLocation != null)
                    locationManager.stopLocationUpdates()
                // TODO can't get location
            }
            // TODO can't get location
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentPrayersBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.executePendingBindings()

        locationManager = MyLocationManager(fusedLocationClient, locationCallback,requireContext())
        if (!MyPermissions.isPermissionsGranted(this.context, locationPermissions))
            MyPermissions.requestPermissionFragment(this, locationPermissions, LOCATION_PERMISSIONS_REQUEST_CODE)
        else
            locationManager.startLocationUpdates()


        viewModel.back.observe(viewLifecycleOwner) {
            if (it != null && it) {
                this.findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                mainActivityEventsListener.showErrorMessage(it)
                viewModel.onErrorMessageShown()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it != null && it)
                mainActivityEventsListener.showLoading()
            else
                mainActivityEventsListener.hideLoading()
        }



        return binding.root
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == LOCATION_PERMISSIONS_REQUEST_CODE &&
            grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            locationManager.startLocationUpdates()
    }


}