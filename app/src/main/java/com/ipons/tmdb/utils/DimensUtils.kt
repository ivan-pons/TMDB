package com.ipons.tmdb.utils

import android.content.res.Resources
import kotlin.math.roundToInt

object DimensUtils {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).roundToInt()
    }
}