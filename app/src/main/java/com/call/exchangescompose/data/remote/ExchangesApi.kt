package com.call.exchangescompose.data.remote

import com.call.exchangescompose.data.remote.dto.ExchangesDto
import retrofit2.http.GET

interface ExchangesApi {
    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<ExchangesDto>
}