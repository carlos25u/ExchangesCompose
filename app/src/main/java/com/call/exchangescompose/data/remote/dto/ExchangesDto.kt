package com.call.exchangescompose.data.remote.dto

data class ExchangesDto (
    val name: String,
    val description: String ?,
    val active: Boolean = false,
    val last_updated: String
    )