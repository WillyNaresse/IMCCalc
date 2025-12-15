package com.willy.imccalc.data

import kotlinx.coroutines.flow.Flow

interface IMCCalcRepository {
    suspend fun insert(entity: IMCCalcEntity)
    suspend fun delete(entity: IMCCalcEntity)
    fun getAll(): Flow<List<IMCCalcEntity>>
    suspend fun getById(id: Long): IMCCalcEntity?
}