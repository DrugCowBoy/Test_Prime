package com.dot.prime.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.provider.Settings
import android.text.InputFilter
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dot.prime.R
import com.dot.prime.presentation.common.custom_view.CustomTypefaceSpan
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern
import kotlin.math.roundToInt

// Create SnackBar
fun View.createSnackBar(message: String, actionOk: String){
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        .setAction(actionOk){}
        .setActionTextColor(resources.getColor(R.color.black, null))// Color Ok
        .setBackgroundTint(resources.getColor(R.color.white, null))// Color SnackBar
        .setTextColor(resources.getColor(R.color.black, null))// Color text
        .show()
}

// Convert dp to px
fun Int.dpToPx(context: Context?): Int {
    val density = context?.resources?.displayMetrics?.density
    return if (density != null){
        (this.toFloat() * density).roundToInt()
    }else{
        0
    }
}

// Convert dp to px (Float)
fun Int.dpToPxFloat(context: Context?): Float {
    val density = context?.resources?.displayMetrics?.density
    return if (density != null){
        this.toFloat() * density
    }else{
        0f
    }
}

// Text with underline
fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

// Round number to zero symbol after point (1.11 -> 1; 1.00 -> 1)
fun String.roundToInt(): String {
    return this.substringBefore(".")
}

// Remove zeros after point for String
fun String.removeZeros(): String {
    if (this.contains(".00")) {
        return this.replace(".00", "")
    }else if(this.contains(".0")){
        return this.replace(".0", "")
    } else{
        return this
    }
}

// Remove zeros after point for Double
fun Double.removeZeros(): String {
    val number = this.toString()
    return number.removeZeros()
}

// Convert date
fun String.convertDate(): String{
    var outputDate = ""
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM", Locale.getDefault())
        val inputDate = inputFormat.parse(this) ?: ""
        outputDate = outputFormat.format(inputDate)
    }catch (exception: Exception){ }
    return outputDate
}

// Convert phone number for profile
fun String.convertPhoneNumber(): String {
    return replaceFirst(
        "^\\+?(\\d{1})(\\d{3})(\\d{3})(\\d{2})(\\d{2})$".toRegex(),
        "+$1 $2 $3-$4-$5"
    )
}

// Set custom EditText
fun EditText.setParams(
    radiusField: Int = 10,
    colorTextField: String = "#000000",
    thicknessBorder: Int = 2,
    colorField: String? = null,
    colorBorderField: String? = null,
    colorHintField: String? = null,
){
    this.setTextColor(Color.parseColor(colorTextField))
    if (colorHintField != null){
        this.setHintTextColor(Color.parseColor(colorHintField))
    }else{
        this.setHintTextColor(Color.parseColor(colorTextField))
    }
    val shape = GradientDrawable()
    shape.shape = GradientDrawable.RECTANGLE
    shape.cornerRadius = radiusField.dpToPx(context).toFloat()
    if (colorField != null){
        shape.setColor(Color.parseColor(colorField))
    }else{
        shape.setColor(Color.TRANSPARENT)
    }
    if (colorBorderField != null){
        shape.setStroke(thicknessBorder.dpToPx(context), Color.parseColor(colorBorderField))
    }
    this.background = shape
}

// Set custom SearchView
fun SearchView.setParams(maxSymbols: Int, radiusField: Int, colorField: String? = null, colorTextField: String, colorHintTextField: String){
    // Set max length for SearchView
    val et = this.findViewById<View>(
        this.context.resources.getIdentifier("android:id/search_src_text", null, null)
    ) as EditText
    et.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxSymbols))

    // Set color text
    et.setTextColor(Color.parseColor(colorTextField))
    et.setHintTextColor(Color.parseColor(colorHintTextField))

    // Set background for SearchView
    val shape = GradientDrawable()
    shape.shape = GradientDrawable.RECTANGLE
    shape.cornerRadius = radiusField.dpToPx(context).toFloat()
    if (colorField != null){
        shape.setColor(Color.parseColor(colorField))
    }else{
        shape.setColor(Color.TRANSPARENT)
    }
    this.background = shape
}

// Set text bold
fun String.makeBoldSymbols(context: Context, regex: String): SpannableStringBuilder{
    val pattern = Pattern.compile(regex)
    val spannable = SpannableStringBuilder(this)
    val matcher = pattern.matcher(this)
    try {
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            spannable.setSpan(
                CustomTypefaceSpan("", ResourcesCompat.getFont(context, R.font.object_sans_heavy)!!),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }catch (_: Exception){}
    return spannable
}

// Get name month from number month
fun Int.getMonthGroup(context: Context?, defaultMonth: String): String{
    return when(this){
        1 -> context?.getString(R.string.group_january) ?: defaultMonth
        2 -> context?.getString(R.string.group_february) ?: defaultMonth
        3 -> context?.getString(R.string.group_march) ?: defaultMonth
        4 -> context?.getString(R.string.group_april) ?: defaultMonth
        5 -> context?.getString(R.string.group_may) ?: defaultMonth
        6 -> context?.getString(R.string.group_june) ?: defaultMonth
        7 -> context?.getString(R.string.group_july) ?: defaultMonth
        8 -> context?.getString(R.string.group_august) ?: defaultMonth
        9 -> context?.getString(R.string.group_september) ?: defaultMonth
        10 -> context?.getString(R.string.group_october) ?: defaultMonth
        11 -> context?.getString(R.string.group_november) ?: defaultMonth
        else -> context?.getString(R.string.group_december) ?: defaultMonth
    }
}

// Increase month on 1 number
fun Int.increaseMonth(): Int{
    val number = this
    if (number in 1..11){
        return number + 1
    }else{
        return 1
    }
}

// Get Bitmap from vector image
fun Int.createBitmapFromVector(context: Context?): Bitmap? {
    val art = this
    val drawable = context?.let { ContextCompat.getDrawable(it, art) } ?: return null
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    ) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

// Set images in text
fun TextView.setTextAndImages(text: String, listResImage: List<Int>, size: Int){
    try{
        val replacer = "[image]"
        val ssb = SpannableStringBuilder(text)
        var textInput = text
        listResImage.forEach { resImage ->
            val start = textInput.indexOf(replacer)
            textInput = textInput.replaceFirst(replacer, "-image-")
            val drawable = ContextCompat.getDrawable(this.context, resImage) ?: return
            drawable.mutate()
            drawable.setBounds(0, 0, size, size)
            ssb.setSpan(ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM), start, start + replacer.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        this.setText(ssb, TextView.BufferType.SPANNABLE)
    }catch (exception: Exception){
        Log.i("My", "Error setTextAndImages: $text")
    }
}

// Get device id
@SuppressLint("HardwareIds")
fun Context.deviceId() = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

// Set margins for view
fun View.margin(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null, context: Context?) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = this.dpToPx(context) }
        top?.run { topMargin = this.dpToPx(context) }
        right?.run { rightMargin = this.dpToPx(context) }
        bottom?.run { bottomMargin = this.dpToPx(context) }
    }
}
inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun String.convertDatePersonalSales(): String{
    // Formatters
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    // Parsing string in LocalDateTime
    val dateTime = LocalDateTime.parse(this, inputFormatter)
    // Format LocalDateTime in output
    return dateTime.format(outputFormatter)
}
