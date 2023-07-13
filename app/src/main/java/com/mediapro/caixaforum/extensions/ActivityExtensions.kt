package com.mediapro.caixaforum.extensions

import android.app.Activity
import java.util.Locale

fun Activity.changeLang(langCode: String) {
    val locale = Locale(langCode)
    val config = resources.configuration
    Locale.setDefault(Locale(langCode))
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}