package com.agolygin.fakestoreapitest.network

import com.agolygin.fakestoreapitest.data.ProductData
import retrofit2.http.*

interface ApiRestService {

    @Headers("Content-Type: application/json")
    @GET("products")
    suspend fun getProducts() : ArrayList<ProductData>
}