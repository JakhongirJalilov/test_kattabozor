package com.example.kattabozortest.data.remote.repository

import com.example.kattabozortest.data.remote.response.ProductResponse
import com.example.kattabozortest.data.remote.service.ProductService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productService: ProductService) {
    suspend fun getProducts(): ProductResponse {
        return productService.getProducts()
    }
}