package com.example.kattabozortest.presentation.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kattabozortest.data.remote.response.ProductResponse
import com.example.kattabozortest.domain.usecase.product.ProductUseCase
import com.example.kattabozortest.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {
    private var _products = MutableSharedFlow<ResultWrapper<ProductResponse>>()
    val products: MutableSharedFlow<ResultWrapper<ProductResponse>> = _products

    fun getProducts() {
        viewModelScope.launch {
            ResultWrapper.Loading
            _products.emit(ResultWrapper.Success(productUseCase.getProducts()))
        }
    }
}