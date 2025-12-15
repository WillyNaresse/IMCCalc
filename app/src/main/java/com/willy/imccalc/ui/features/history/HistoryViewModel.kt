package com.willy.imccalc.ui.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willy.imccalc.data.IMCCalcRepository
import com.willy.imccalc.domain.IMC
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: IMCCalcRepository
) : ViewModel() {
    val historyState: StateFlow<List<IMC>> = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.DeleteImc -> {
                viewModelScope.launch {
                    repository.delete(event.imc.id)
                }
            }
        }
    }
}