package com.example.kattabozortest.domain.usecase.jokes

import com.example.kattabozortest.data.remote.response.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    suspend fun getProducts(): ProductResponse
}