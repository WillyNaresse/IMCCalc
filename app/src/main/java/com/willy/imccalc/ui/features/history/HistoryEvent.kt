package com.willy.imccalc.ui.features.history

import com.willy.imccalc.domain.IMC

sealed interface HistoryEvent {
    data class DeleteImc(val imc: IMC) : HistoryEvent
}