package com.willy.imccalc.data

import com.willy.imccalc.domain.IMC
import kotlinx.coroutines.flow.Flow
import java.sql.Date
import java.time.LocalDate

interface IMCCalcRepository {
    suspend fun insert(height: Long, width: Double, result: Double, date: LocalDate, id: Long? = null)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<IMC>>

    suspend fun getById(id: Long): IMC?
}