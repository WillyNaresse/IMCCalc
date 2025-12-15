package com.willy.imccalc.ui.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willy.imccalc.data.IMCCalcEntity
import com.willy.imccalc.data.IMCCalcRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: IMCCalcRepository
) : ViewModel() {

    private val _state = MutableStateFlow<IMCCalcEntity?>(null)
    val state = _state.asStateFlow()

    fun load(id: Long) {
        viewModelScope.launch {
            _state.value = repository.getById(id)
        }
    }
}