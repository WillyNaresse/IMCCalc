package com.willy.imccalc.data

import kotlinx.coroutines.flow.Flow

class IMCCalcRepositoryImpl(
    private val dao: IMCCalcDao
) : IMCCalcRepository {

    override suspend fun insert(entity: IMCCalcEntity) = dao.insert(entity)

    override suspend fun delete(entity: IMCCalcEntity) = dao.delete(entity)

    override fun getAll(): Flow<List<IMCCalcEntity>> = dao.getAll()

    override suspend fun getById(id: Long): IMCCalcEntity? = dao.getById(id)
}