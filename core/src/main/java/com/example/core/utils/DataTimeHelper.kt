package com.example.core.utils

import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DataTimeHelper {
    fun stringDataTimeToLocalTime(dataTime: String): String {
        val time = ZonedDateTime.parse(dataTime).toLocalDateTime()
        return DateTimeFormatter.ofPattern("E , d/MM/yyyy , h:mm a")
            .format(time)
    }

}


fun String.stringDataTimeToLocalTime(pattern: String = "E , d/MM/yyyy , h:mm a"): String {
    val time = ZonedDateTime.parse(this).toLocalDateTime()
    return DateTimeFormatter.ofPattern(pattern)
        .format(time)
}

fun String.toDate(format: String = "yyyy-MM-dd HH:mm:ss"): Date? {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.parse(this)
}

fun Date.toStringFormat(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}