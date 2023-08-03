package com.ipons.tmdb.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ipons.tmdb.extensions.loadImage

@BindingAdapter("urlImage")
fun bindingImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.loadImage(url)
    }
}

@BindingAdapter("bindFromResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("clipShape")
fun bindingClipShape(view: View, isClipped: Boolean?) {
    view.clipToOutline = isClipped ?: false
}