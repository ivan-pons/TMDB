package com.ipons.tmdb.view.menu

import android.animation.ValueAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.ipons.tmdb.R
import com.ipons.tmdb.databinding.TvSideMenuLayoutBinding

class TvLeftMenuView : LinearLayout {

    private lateinit var binding: TvSideMenuLayoutBinding

    companion object {
        const val HOME_MENU = 11
        const val SEARCH_MENU = 12
        const val SETTING_MENU = 13
        const val MY_SPACE_MENU = 14

        fun animateView(view: View, valueAnimator: ValueAnimator) {
            valueAnimator.addUpdateListener { animation ->
                view.layoutParams.width = animation.animatedValue as Int
                view.requestLayout()
            }

            valueAnimator.interpolator = AccelerateInterpolator()
            valueAnimator.duration = 200
            valueAnimator.start()
        }
    }

    private var menuItemClick: MenuItemClickListener? = null
    private var currentSelected: Int = HOME_MENU
    private var leftMenusShown: Boolean = false

    private var mContext: Context? = null

    interface MenuItemClickListener {
        fun menuItemClick(menuId: Int)
    }

    constructor(context: Context) : super(context) {
        setupMenuUI(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupMenuUI(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setupMenuUI(context)
    }

    private fun setupMenuUI(context: Context) {
        this.mContext = context
        this.menuItemClick = context as MenuItemClickListener
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setBackgroundColor(ContextCompat.getColor(this.context, R.color.transparent))

        binding = TvSideMenuLayoutBinding.inflate(LayoutInflater.from(context), this)

        binding.cllSearchView.setOnClickListener {
            menuItemClick!!.menuItemClick(SEARCH_MENU)
        }

        binding.cllHomeView.setOnClickListener {
            menuItemClick!!.menuItemClick(HOME_MENU)
        }

        binding.cllSettingsView.setOnClickListener {
            menuItemClick!!.menuItemClick(SETTING_MENU)
        }

    }

    private fun focusCurrentSelectedMenu() {
        when (currentSelected) {
            HOME_MENU -> {
                binding.cllHomeView.apply {
                    requestFocus()
                }
            }

            SEARCH_MENU -> {
                binding.cllSearchView.apply {
                    requestFocus()
                }
            }

            SETTING_MENU -> {
                binding.cllSettingsView.apply {
                    requestFocus()
                }
            }
        }
    }

    fun setCurrentSelected(currentSelected: Int) {
        this.currentSelected = currentSelected
    }

    fun getCurrentSelected(): Int {
        return currentSelected
    }

    private fun highlightCurrentSelectedMenu() {
        binding.civHome.isSelected = currentSelected == HOME_MENU
        binding.civSearch.isSelected = currentSelected == SEARCH_MENU
        binding.civSettings.isSelected = currentSelected == SETTING_MENU
    }

    fun setupMenuExpandedUI() {
        Handler(Looper.getMainLooper()).postDelayed({
            setBackgroundColor(ContextCompat.getColor(this.context, R.color.black_trans_88))
            changeMenuFocusStatus(true)
            focusCurrentSelectedMenu()
        }, 100)
    }

    fun setupMenuClosedUI() {
        changeMenuFocusStatus(false)
        setBackgroundColor(ContextCompat.getColor(this.context, R.color.transparent))
    }

    private fun changeMenuFocusStatus(status: Boolean) {
        val count = childCount
        for (i in 0 until count) {
            val childView = getChildAt(i)
            childView.apply {
                isFocusable = status
                isFocusableInTouchMode = status
                if (!status) {
                    clearFocus()
                    highlightCurrentSelectedMenu()
                } else {
                    setBackgroundColor(0)
                    background =
                        ContextCompat.getDrawable(this.context, R.drawable.menu_bg_selector)
                    changeMenuColor()
                }
            }
        }
    }

    private fun changeMenuColor() {
        binding.focusHome.setBackgroundResource(R.drawable.focus_line_selector)

        binding.focusSearch.setBackgroundResource(R.drawable.focus_line_selector)

        binding.focusSettings.setBackgroundResource(R.drawable.focus_line_selector)
    }

    fun setupDefaultMenu(menuId: Int) {
        currentSelected = menuId
        leftMenusShown = false
        changeMenuFocusStatus(false)
    }

}
