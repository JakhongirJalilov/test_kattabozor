package com.example.kattabozortest.domain.usecase.product

import com.example.kattabozortest.data.remote.response.ProductResponse

interface ProductUseCase {
    suspend fun getProducts(): ProductResponse
}