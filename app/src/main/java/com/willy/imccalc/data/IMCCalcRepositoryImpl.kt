package com.willy.imccalc.data

import com.willy.imccalc.domain.IMC
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class IMCCalcRepositoryImpl(
    private val dao: IMCCalcDao
): IMCCalcRepository {
    override suspend fun insert(height: Long, width: Double, result: Double, date: LocalDate, id: Long?) {
        val entity = id?.let {
            dao.getById(it)?.copy(
                height = height,
                width = width,
                result = result,
                date = date
            )
        } ?: IMCCalcEntity(
            height = height,
            width = width,
            result = result,
            date = date
        )

        dao.insert(entity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = dao.getById(id) ?: return
        dao.delete(existingEntity)
    }

    override fun getAll(): Flow<List<IMC>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                IMC(
                    id = entity.id,
                    height = entity.height,
                    width = entity.width,
                    result = entity.result,
                    date = entity.date
                )
            }
        }
    }

    override suspend fun getById(id: Long): IMC? {
        return dao.getById(id)?.let { entity ->
            IMC(
                id = entity.id,
                height = entity.height,
                width = entity.width,
                result = entity.result,
                date = entity.date
            )
        }
    }
}