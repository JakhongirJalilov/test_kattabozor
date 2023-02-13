package com.example.kattabozortest.domain.usecase.jokes

import com.example.kattabozortest.data.remote.repository.ProductRepository
import com.example.kattabozortest.data.remote.response.ProductResponse
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : ProductUseCase {
    override suspend fun getProducts(): ProductResponse =
        productRepository.getProducts()
}