package com.willy.imccalc.ui.features.home

sealed interface HomeEvent {
    data class HeightChanged(val height: String) : HomeEvent
    data class WeightChanged(val weight: String) : HomeEvent
    data class goToHistory(val id: Long?) : HomeEvent
    data object Save : HomeEvent
}