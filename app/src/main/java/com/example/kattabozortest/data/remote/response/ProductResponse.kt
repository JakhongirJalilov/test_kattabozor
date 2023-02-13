package com.example.kattabozortest.data.remote.response

@kotlinx.serialization.Serializable
data class ProductResponse(
    val offers: List<Offer>
)