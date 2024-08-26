package com.example.stepogotchi_main.data.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun getCurrentDateString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(Date())
}