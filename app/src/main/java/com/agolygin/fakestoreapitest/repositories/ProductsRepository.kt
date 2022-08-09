package com.agolygin.fakestoreapitest.repositories

import com.agolygin.fakestoreapitest.data.ProductData
import com.agolygin.fakestoreapitest.network.ApiRestService
import com.agolygin.fakestoreapitest.network.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsRepository(private val apiService: ApiRestService) : IProductsRepository {

    override suspend fun getProducts(): ResultWrapper<ArrayList<ProductData>> {
        var result: ResultWrapper<ArrayList<ProductData>>
        withContext(Dispatchers.IO) {
            try {
                result = ResultWrapper.Success(apiService.getProducts())
            } catch (throwable: Throwable) {
                result = ResultWrapper.GenericError
            }
        }
        return result
    }
}