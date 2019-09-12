package com.andhicaaa.corelibrary.extensions

import android.util.Patterns
import com.andhicaaa.corelibrary.util.TextUtil
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by instagram : @andhicaaa on 9/12/2019.
 */

inline fun <reified T : Any> clazz() = T::class.java

fun String.isNumeric(): Boolean = matches("^[0-9]+\$".toRegex())

fun String.isEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.convertPhoneFormat(countryCode: String): String {
    if (this.isNotEmpty()) {
        var result = this.trim().replace("\\s".toRegex(), TextUtil.BLANK)
        if (result.startsWith("0", true)) {
            result = "$countryCode${result.substring(1)}"
        }
        return result
    }
    return TextUtil.BLANK
}

fun Long.convertToCurrency(countryCurrency: String = TextUtil.BLANK): String {
    val value = NumberFormat.getNumberInstance().format(this).replace(",", ".")
    return "$countryCurrency $value"
}

fun Int.convertToCurrency(countryCurrency: String = TextUtil.BLANK): String {
    val value = NumberFormat.getNumberInstance().format(this).replace(",", ".")
    return "$countryCurrency $value"
}

fun String.convertDate(inputFormat: String = "yyyy-MM-dd", outputFormat: String = "dd MMMM yyyy"): String {
    val dateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
    val requiredFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
    try {
        val date = dateFormat.parse(this)
        return requiredFormat.format(date)
    } catch (e: Exception) {
    }
    return TextUtil.BLANK
}