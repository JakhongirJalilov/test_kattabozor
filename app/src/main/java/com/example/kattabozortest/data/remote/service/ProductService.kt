package com.example.kattabozortest.data.remote.service

import com.example.kattabozortest.data.remote.response.ProductResponse
import retrofit2.http.GET

interface ProductService {
    @GET("v1/offers")
    suspend fun getProducts(): ProductResponse
}