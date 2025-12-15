package com.willy.imccalc.ui.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willy.imccalc.data.Calculations
import com.willy.imccalc.data.IMCCalcRepository
import com.willy.imccalc.navigation.HistoryRoute
import com.willy.imccalc.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val id: Long? = null,
    private val repository: IMCCalcRepository,
) : ViewModel() {
    var height by mutableStateOf("")
        private set

    var weight by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.HeightChanged -> {
                height = event.height
            }

            is HomeEvent.WeightChanged -> {
                weight = event.weight
            }

            is HomeEvent.goToHistory -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(HistoryRoute(null)))
                }
            }

            HomeEvent.Save -> {
                saveIMCCalc()
            }
            else -> {}
        }
    }

    private fun saveIMCCalc() {
        viewModelScope.launch {
            if (height.isBlank() || weight.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("Adicione sua altura e peso."))
                return@launch
            }

            val result = Calculations.calculateIMC(height, weight)
            val now = java.time.LocalDate.now()

            val imcLevel = Calculations.showIMCLevel(result)
            _uiEvent.send(UiEvent.ShowResult(imcLevel))

            repository.insert(height.toLong(), weight.toDouble(), result, now)
        }
    }
}