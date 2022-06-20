package com.call.exchangescompose.di

import com.call.exchangescompose.data.remote.ExchangesApi
import com.call.exchangescompose.data.remote.repository.ExchangesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangesApi(moshi: Moshi): ExchangesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExchangesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangesRepository(exchangesApi: ExchangesApi): ExchangesRepository {
        return ExchangesRepository(exchangesApi)
    }
}