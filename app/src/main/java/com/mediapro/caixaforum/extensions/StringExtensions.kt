package com.mediapro.caixaforum.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.util.Locale

fun String.fromHtml(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun String.capitalized(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}




