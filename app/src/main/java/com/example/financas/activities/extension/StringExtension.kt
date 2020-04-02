package com.example.financas.activities.extension

import java.text.SimpleDateFormat
import java.util.*
import kotlin.String as string

fun string.sliceIfHasMoreSizeThan(limitSize: Int): string {
    if (this.length > limitSize) {
        return "${this.substring(0, limitSize)}..."
    }

    return this
}

fun string.convertToCalendar(): Calendar {
    val desiredFormat = SimpleDateFormat("dd/MM/yyyy")
    val converted = desiredFormat.parse(this)

    var date = Calendar.getInstance()

    date.time = converted
    return date
}