package com.willy.imccalc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [IMCCalcEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class IMCCalcDatabase : RoomDatabase() {
    abstract val dao: IMCCalcDao
}

object IMCCalcDatabaseProvider {

    @Volatile
    private var INSTANCE: IMCCalcDatabase? = null

    fun provide(context: Context): IMCCalcDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                IMCCalcDatabase::class.java,
                "imccalc-db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}