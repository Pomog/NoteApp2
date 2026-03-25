package com.example.noteapp2.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampsFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateFromTimesDate(timeStamp: Long): Date? {
        return Date(timeStamp)
    }
}