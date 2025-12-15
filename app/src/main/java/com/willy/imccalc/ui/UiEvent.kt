package com.willy.imccalc.ui

interface UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent
    data class Navigate<T : Any>(val route: T) : UiEvent
    data class ShowResult(val result: String) : UiEvent
    data object NavigateBack : UiEvent
}

