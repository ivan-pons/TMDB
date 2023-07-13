package com.mediapro.caixaforum.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ViewGroup.dataBindingInflate(
    @LayoutRes res: Int,
    attachToRoot: Boolean = false
): T = DataBindingUtil.inflate(LayoutInflater.from(context), res, this, attachToRoot)