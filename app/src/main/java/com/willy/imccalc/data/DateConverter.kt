package com.willy.imccalc.data

import androidx.room.TypeConverter
import java.time.LocalDate

object DateConverter {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString()
    }
}