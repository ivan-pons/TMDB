package com.ipons.tmdb.view.home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.leanback.widget.HorizontalGridView

class HomeConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    lateinit var openMenu: () -> Unit

    fun setOpenMenuListener(focusClicked: () -> Unit) {
        this.openMenu = focusClicked
    }

    override fun focusSearch(focused: View, direction: Int): View {
        val newFocus = super.focusSearch(focused, direction) ?: focused

        if (direction == View.FOCUS_LEFT) {
            if (focused.parent is HorizontalGridView) {
                if (this::openMenu.isInitialized) openMenu()
                return focused
            }
        }

        return newFocus
    }

}