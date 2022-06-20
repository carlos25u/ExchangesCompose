package com.call.exchangescompose.data.remote.repository

import com.call.exchangescompose.data.remote.ExchangesApi
import com.call.exchangescompose.data.remote.dto.ExchangesDto
import com.call.exchangescompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExchangesRepository @Inject constructor(
    private val api: ExchangesApi
){
    fun getExchanges(): Flow<Resource<List<ExchangesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val exchanges = api.getExchanges()
            emit(Resource.Success(exchanges))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}