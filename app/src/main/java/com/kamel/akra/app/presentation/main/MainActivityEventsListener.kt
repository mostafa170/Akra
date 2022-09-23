package com.kamel.akra.app.presentation.main

interface MainActivityEventsListener {
    fun showLoading()
    fun hideLoading()
    fun showErrorMessage(message: String)
    fun showSuccessMessage(message: String)
    fun userUnauthenticated()
}