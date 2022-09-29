package com.kamel.akra.app.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.kamel.akra.R
import com.kamel.akra.app.utilsView.MyDialog
import com.kamel.akra.data.utils.SharedPreferencesData
import com.kamel.akra.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityEventsListener,
    NavController.OnDestinationChangedListener  {

    private val myDialog: MyDialog by lazy { MyDialog(this) }
    private var currentDestinationId: Int = 0
    private lateinit var  binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    private val navOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()

//        if(!SharedPreferencesData.isFirstOpen()){
//            navController.navigate(R.id.homeFragment, null, navOptions)
//        } else
//            SharedPreferencesData.setIsFirstOpen(false)

        navController = this.findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        currentDestinationId = destination.id
    }

    override fun showLoading() {
        myDialog.showLoadingDialog()
    }

    override fun hideLoading() {
        myDialog.hideLoadingDialog()
    }

    override fun showErrorMessage(message: String) {
        myDialog.showErrorMessageDialog(message)
    }

    override fun showSuccessMessage(message: String) {
        myDialog.showSuccessMessageDialog(message)
    }

    override fun userUnauthenticated() {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        if (currentDestinationId == R.id.homeFragment || currentDestinationId ==R.id.onBoardFragment) {
            finish()
        }else {
            super.onBackPressed()
        }
    }
}