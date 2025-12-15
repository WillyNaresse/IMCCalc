package com.willy.imccalc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "imccalcs")
data class IMCCalcEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val height: Long,
    val width: Double,
    val result: Double,
    val date: LocalDate
)
