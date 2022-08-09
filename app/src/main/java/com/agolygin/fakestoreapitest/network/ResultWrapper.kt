package com.agolygin.fakestoreapitest.network

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    object GenericError: ResultWrapper<Nothing>()
}