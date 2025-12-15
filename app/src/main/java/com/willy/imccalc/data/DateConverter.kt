package com.willy.imccalc.data

import androidx.room.TypeConverter
import java.time.LocalDateTime

object DateConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toString(dateTime: LocalDateTime?): String? {
        return dateTime?.toString()
    }
}