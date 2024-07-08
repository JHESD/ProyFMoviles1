package com.example.proyfinalmv2.models.Rest

typealias RestSnFilts = ArrayList<RestSnFilt>

data class RestSnFilt(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val description: String,

    val userID: Long,
    val logo: String
)