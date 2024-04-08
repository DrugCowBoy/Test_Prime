package com.example.testprime.common

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

// Replace a few letters in word
fun String.replacePair(vararg replacements: Pair<String, String>): String {
    var result = this
    replacements.forEach { (l, r) -> result = result.replace(l, r) }
    return result
}

fun String.formatDate(): String{
    if (this.length == "dd/MM/yyyy".length){
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val isoDate = isoFormat.format(date)
        Log.i("Log_prime", "Date ISO: $isoDate")
        return isoDate
    }else{
        return this
    }
}



