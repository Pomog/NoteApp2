package com.example.noteapp2.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern(
        "EEE, d MMM hh:mm a",
        Locale.getDefault()
    )
    return dateTime.format(formatter)
}
