package com.ipons.tmdb.base

import androidx.fragment.app.Fragment
import com.ipons.tmdb.MainActivity

open class BaseFragment : Fragment() {

    fun openMenu() {
        val activity = requireActivity()
        if (activity is MainActivity) activity.requestMenuFocus()
    }
}