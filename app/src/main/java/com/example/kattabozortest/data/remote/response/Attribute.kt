package com.example.kattabozortest.data.remote.response

@kotlinx.serialization.Serializable
data class Attribute(
    val name: String,
    val value: String
)