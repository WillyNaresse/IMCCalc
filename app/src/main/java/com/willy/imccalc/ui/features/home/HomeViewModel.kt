package com.willy.imccalc.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willy.imccalc.data.Calculations
import com.willy.imccalc.data.IMCCalcEntity
import com.willy.imccalc.data.IMCCalcRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel(
    private val repository: IMCCalcRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun calculate(
        height: Long,
        weight: Double,
        age: Int,
        sex: String,
        activityFactor: Double
    ) {
        val imc = Calculations.calculateIMC(height, weight)
        val classification = Calculations.imcClassification(imc)
        val tmb = Calculations.calculateTMB(weight, height, age, sex)
        val idealWeight = Calculations.calculateIdealWeight(height, sex)
        val calories = Calculations.calculateDailyCalories(tmb, activityFactor)

        val entity = IMCCalcEntity(
            dateTime = LocalDateTime.now(),
            height = height,
            weight = weight,
            age = age,
            sex = sex,
            imc = imc,
            imcClassification = classification,
            tmb = tmb,
            idealWeight = idealWeight,
            dailyCalories = calories
        )

        viewModelScope.launch {
            repository.insert(entity)
        }

        _state.value = HomeUiState(result = entity)
    }
}