package com.call.exchangescompose.ui.theme.componentes

import com.call.exchangescompose.data.remote.dto.ExchangesDto

data class ExchangesListState (
    val isLoading: Boolean = false,
    val exchanges: List<ExchangesDto> = emptyList(),
    val error: String = ""
        )