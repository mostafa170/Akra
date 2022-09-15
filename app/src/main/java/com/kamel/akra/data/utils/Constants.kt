package com.kamel.akra.data.utils

import android.Manifest

const val DEFAULT_LANGUAGE = "en"

const val UN_AUTH: String = "UnauthenticatedFailure"
const val ACCEPT_ORDER_FAIL: String = "AcceptOrderFailure"
const val ORDER_ID: String = "orderID"

const val PREF_KEY_APP_LANGUAGE = "language"
const val PREF_KEY_APP = "languageData"

const val SUCCESS: Int = 200
const val SUCCESS201: Int = 201
const val USER_UNAUTHENTICATED: Int = 401
const val ORDER_WITH_OTHER_CAPTAIN: Int = 405
const val CAPTURE_IMAGES_PERMISSION: Int = 1050
const val READ_EXTERNAL_STORAGE_PERMISSION: Int = 1055
const val LOCATION_PERMISSIONS_REQUEST_CODE: Int = 5000
const val PERMISSIONS_REQUEST_ENABLE_GPS: Int = 600
const val CAMERA_REQUEST_CODE1: Int = 0
const val GALLERY_REQUEST_CODE1: Int = 2

const val CAMERA_REQUEST_CODE2: Int = 1
const val GALLERY_REQUEST_CODE2: Int = 3

const val CAMERA_REQUEST_CODE3: Int = 4
const val GALLERY_REQUEST_CODE3: Int = 6

const val CAMERA_REQUEST_CODE4: Int = 5
const val GALLERY_REQUEST_CODE4: Int = 7

const val CAMERA_REQUEST_CODE: Int = 10
const val GALLERY_REQUEST_CODE: Int = 12

const val DEVICE_TYPE_ID: String = "1"

const val MINIMUM_TIME_BETWEEN_UPDATES = (5 * 1000).toLong()
const val MINIMUM_DISTANCE_BETWEEN_UPDATES: Float = 5.0F
val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)
var cameraPermissions = arrayOf(Manifest.permission.CAMERA)
var storagePermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)


