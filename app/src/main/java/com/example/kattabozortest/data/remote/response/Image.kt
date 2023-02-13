package com.example.kattabozortest.data.remote.response

@kotlinx.serialization.Serializable
data class Image(
    val height: String,
    val url: String,
    val width: String
)