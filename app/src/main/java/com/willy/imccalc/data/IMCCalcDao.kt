package com.willy.imccalc.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IMCCalcDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: IMCCalcEntity)

    @Delete
    suspend fun delete(entity: IMCCalcEntity)

    @Query("SELECT * FROM imccalcs ORDER BY dateTime DESC")
    fun getAll(): Flow<List<IMCCalcEntity>>

    @Query("SELECT * FROM imccalcs WHERE id = :id")
    suspend fun getById(id: Long): IMCCalcEntity?
}