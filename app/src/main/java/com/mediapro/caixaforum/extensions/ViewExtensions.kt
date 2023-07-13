package com.mediapro.caixaforum.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, placeholder: Drawable? = null) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}
