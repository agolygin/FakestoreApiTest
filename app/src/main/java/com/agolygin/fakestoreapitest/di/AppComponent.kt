package com.agolygin.fakestoreapitest.di

import com.agolygin.fakestoreapitest.MainViewModel
import com.agolygin.fakestoreapitest.di.modules.NetworkModule
import com.agolygin.fakestoreapitest.di.modules.ProductsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    ProductsModule::class])

interface AppComponent {
    fun inject(model: MainViewModel)

}