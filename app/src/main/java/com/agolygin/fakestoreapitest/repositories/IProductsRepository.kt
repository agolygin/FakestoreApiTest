package com.agolygin.fakestoreapitest.repositories

import com.agolygin.fakestoreapitest.data.ProductData
import com.agolygin.fakestoreapitest.network.ResultWrapper

interface IProductsRepository {

    suspend fun getProducts(): ResultWrapper<ArrayList<ProductData>>
}