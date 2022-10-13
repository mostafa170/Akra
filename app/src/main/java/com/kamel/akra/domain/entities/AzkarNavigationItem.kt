package com.kamel.akra.domain.entities

import androidx.navigation.NavDirections

data class AzkarNavigationItem(
    val title: String,
    val imageResourceId: Int,
    val destinationId: NavDirections? = null,
)
