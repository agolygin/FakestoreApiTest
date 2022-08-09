package com.agolygin.fakestoreapitest.di.modules

import com.agolygin.fakestoreapitest.network.ApiRestService
import com.agolygin.fakestoreapitest.repositories.IProductsRepository
import com.agolygin.fakestoreapitest.repositories.ProductsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductsModule {

    @Provides
    @Singleton
    fun providesProductsRepository(apiService : ApiRestService): IProductsRepository = ProductsRepository(apiService)

}