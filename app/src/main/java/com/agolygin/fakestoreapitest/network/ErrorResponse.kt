package com.agolygin.fakestoreapitest.network

data class ErrorResponse(
    val error_code: Int,
    val error_description: String, // this is the translated error shown to the user directly from the API
    val causes: Map<String, String> = emptyMap() //this is for errors on specific field on a form
)
//) {
//
//    fun isTokenRefreshNeeded() : Boolean {
//        if(error_description == "This action need a valid token" || error_description == "Session data too old") {
//            return refreshTokenIsNeeded("some token")
//        }
//        return false
//    }
//
//}