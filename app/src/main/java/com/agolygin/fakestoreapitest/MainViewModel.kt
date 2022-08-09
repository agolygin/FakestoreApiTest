package com.agolygin.fakestoreapitest

import androidx.lifecycle.*
import com.agolygin.fakestoreapitest.data.ProductData
import com.agolygin.fakestoreapitest.network.ResultWrapper
import com.agolygin.fakestoreapitest.repositories.IProductsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var productsRepository: IProductsRepository

    val error = MutableLiveData<String>()
    val products = MutableLiveData<ArrayList<ProductData>>()

    fun getProductsData() {
        viewModelScope.launch {
            when(val result = productsRepository.getProducts()) {
                is ResultWrapper.GenericError -> error.value = "Network or Server error"
                is ResultWrapper.Success -> products.value = result.value
            }
        }
    }
}
