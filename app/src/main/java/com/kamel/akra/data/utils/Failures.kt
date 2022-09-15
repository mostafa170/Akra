package com.kamel.akra.data.utils

import okhttp3.ResponseBody
import org.json.JSONObject
import java.lang.StringBuilder
import java.net.URLDecoder
import java.net.URLEncoder

abstract class AppFailure{
    abstract fun toErrorString(): String
}

class UnauthenticatedFailure: AppFailure(){
    override fun toErrorString(): String = "UnauthenticatedFailure"
}

class AcceptOrderFailure: AppFailure(){
    override fun toErrorString(): String = "AcceptOrderFailure"
}

class UserNotVerifiedFailure: AppFailure(){
    override fun toErrorString(): String = "UserNotVerifiedFailure"
}

class SomethingWentWrongFailure(private val message: String): AppFailure(){
    override fun toErrorString(): String = message
}

/**
 * The base API failure responses is always return in the below format.
 * {
 * "success": "false",
 * "code": 404,
 * "message": "User not found",
 * "errors": [
 *      "User not found"
 * ]
 * }
 */
fun ResponseBody.getErrors(): String {
    val errors = mutableListOf<String>()
    try {
        val raw = this.string()
        val encoded = URLEncoder.encode(raw, "ISO-8859-1")
        val result = URLDecoder.decode(encoded, "UTF-8")

        val bodyObject = JSONObject(result)
        val errorsArray = bodyObject.getJSONArray("errors")

        if(errorsArray.length() > 0)
            for (i in 0 until errorsArray.length()) errors.add(errorsArray.get(i).toString())
        else
            errors.add(bodyObject.getString("message"))
    }catch (e: Exception){
        errors.add(e.message?:"Something went wrong!")
    }
    return ApiError(errors).toString()
}

private data class ApiError(val errors: List<String>){
    override fun toString(): String {
        val error = StringBuilder()
        for (i in errors.indices) {
            error.append(errors[i])
            if (i != errors.size - 1) error.append("\n")
        }
        return error.toString()
    }
}