package com.agolygin.fakestoreapitest.di.modules

import com.agolygin.fakestoreapitest.BuildConfig
import com.agolygin.fakestoreapitest.network.ApiRestService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiRestService {
        return retrofit.create(ApiRestService::class.java)
    }
}
