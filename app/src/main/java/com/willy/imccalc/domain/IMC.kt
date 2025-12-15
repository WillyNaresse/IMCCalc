package com.willy.imccalc.domain

import java.time.LocalDate

class IMC (
    val id: Long,
    val height: Long,
    val width: Double,
    val result: Double,
    val date: LocalDate
)

// mocks
val imc1 = IMC(1, 174, 100.2, 33.09, LocalDate.now());
val imc2 = IMC(2, 180, 95.6, 29.51, LocalDate.now().minusDays(1));
val imc3 = IMC(3, 160, 60.7, 23.71, LocalDate.now().minusWeeks(2));