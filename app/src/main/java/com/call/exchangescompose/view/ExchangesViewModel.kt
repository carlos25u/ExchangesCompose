package com.call.exchangescompose.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.exchangescompose.data.remote.repository.ExchangesRepository
import com.call.exchangescompose.ui.theme.componentes.ExchangesListState
import com.call.exchangescompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val exchangesRepository: ExchangesRepository
) : ViewModel(){
    private var _state = mutableStateOf(ExchangesListState())
    val state: State<ExchangesListState> = _state

    init {
        exchangesRepository.getExchanges().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ExchangesListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = ExchangesListState(exchanges = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ExchangesListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

}