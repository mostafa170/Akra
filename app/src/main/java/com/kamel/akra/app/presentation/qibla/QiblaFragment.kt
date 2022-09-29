package com.kamel.akra.app.presentation.qibla

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kamel.akra.data.utils.*
import com.kamel.akra.databinding.FragmentQiblaBinding
import com.google.android.gms.location.*

class QiblaFragment : Fragment() {

    private val viewModel: QiblaViewModel by viewModels()

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            if(locationResult != null) {
                val currentLocation = locationResult.locations.first { it != null }
                if (currentLocation != null) {
                    viewModel.setQiblaDirection(compass.getQiblaDirection(currentLocation))
                    locationManager.stopLocationUpdates()
                }
                // TODO can't get location qibla inactive
            }
            // TODO can't get location qibla inactive
        }
    }

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access LocationServices after onActivityCreated()"
        }
        LocationServices.getFusedLocationProviderClient(activity)
    }

    private lateinit var locationManager: MyLocationManager

    private lateinit var compass: MyCompass
    private var currentAzimuth: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentQiblaBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()

        locationManager = MyLocationManager(fusedLocationClient, locationCallback,requireContext())
        compass = MyCompass(this.context)

        viewModel.back.observe(viewLifecycleOwner) {
            if (it != null && it) {
                findNavController().navigateUp()
                viewModel.onBackNavigated()
            }
        }

        viewModel.currentQiblaDirection.observe(viewLifecycleOwner) {
            if (it != null && it > 0) {
                compass.setListener { azimuth ->
                    currentAzimuth = azimuth
                    // impl qibla background
                    val qiblaBackgroundAnimation: Animation = RotateAnimation(
                        -currentAzimuth, -azimuth,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f
                    ).apply {
                        duration = 500
                        repeatCount = 0
                        fillAfter = true
                    }
                    currentAzimuth = azimuth
                    binding.imageViewQiblaBackground.startAnimation(qiblaBackgroundAnimation)

                    // impl qibla arrow
                    val qiblaArrowAnimation: Animation = RotateAnimation(
                        -(currentAzimuth) + it, -azimuth,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f
                    ).apply {
                        duration = 500
                        repeatCount = 0
                        fillAfter = true
                    }
                    currentAzimuth = azimuth
                    binding.imageViewQiblaArrow.startAnimation(qiblaArrowAnimation)
                }
            }
        }

        if (!MyPermissions.isPermissionsGranted(this.context, locationPermissions))
            MyPermissions.requestPermissionFragment(this, locationPermissions, LOCATION_PERMISSIONS_REQUEST_CODE)
        else
            locationManager.startLocationUpdates()



        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_PERMISSIONS_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                locationManager.startLocationUpdates()
        }
    }

    override fun onResume() {
        super.onResume()
        compass.start()
    }

    override fun onPause() {
        super.onPause()
        compass.stop()
    }


}