package com.willy.imccalc.ui.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willy.imccalc.data.IMCCalcEntity
import com.willy.imccalc.data.IMCCalcRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    repository: IMCCalcRepository
) : ViewModel() {

    val history: StateFlow<List<IMCCalcEntity>> =
        repository.getAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}