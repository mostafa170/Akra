package com.kamel.akra.app.presentation.main

interface MainActivityEventsListener {
    fun showLoading()
    fun hideLoading()
    fun showErrorMessage(title: String)
    fun showSuccessMessage(title: String)
    fun userUnauthenticated()
    fun userLogout()
}