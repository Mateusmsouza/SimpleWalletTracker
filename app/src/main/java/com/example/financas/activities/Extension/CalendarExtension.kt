package com.example.financas.activities.Extension

import java.util.Calendar

fun Calendar.formatToBrazilianFormat(): String{
    val format = java.text.SimpleDateFormat("dd/MM/yyy")

    return format.format(time)
}