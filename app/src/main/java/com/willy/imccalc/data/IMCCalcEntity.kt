package com.willy.imccalc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "imccalcs")
data class IMCCalcEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val dateTime: LocalDateTime,

    val height: Long,
    val weight: Double,
    val age: Int,
    val sex: String,

    val imc: Double,
    val imcClassification: String,

    val tmb: Double,
    val idealWeight: Double,
    val dailyCalories: Double
)