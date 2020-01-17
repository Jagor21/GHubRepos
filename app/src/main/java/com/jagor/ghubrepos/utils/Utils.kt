package com.jagor.ghubrepos.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDateFromString(date: String): String {

    val simpleDateFormatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val simpleDateFormatOutput = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    val d = simpleDateFormatInput.parse(date)

    return simpleDateFormatOutput.format(d)
}