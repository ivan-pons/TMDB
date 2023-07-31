package com.ipons.tmdb

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ipons.tmdb.databinding.ActivityMainBinding
import com.ipons.tmdb.utils.DimensUtils.dpToPx
import com.ipons.tmdb.view.home.HomeFragment
import com.ipons.tmdb.view.menu.TvLeftMenuView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(), TvLeftMenuView.MenuItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private var leftMenusShown: Boolean = false
    private val maxExpandWidth = dpToPx(168)
    private val minExpandWidth = dpToPx(56)
    private var actualFragment: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rlLeftMenu.setupDefaultMenu(TvLeftMenuView.HOME_MENU)
        replaceSelectedFragment(HomeFragment())
        actualFragment = HOME
        setupFocusListener()
    }

    override fun menuItemClick(menuId: Int) {
        if (binding.rlLeftMenu.getCurrentSelected() == menuId) {
            return
        }

        val fragment: Fragment?
        when (menuId) {
            TvLeftMenuView.SEARCH_MENU -> {
                binding.rlLeftMenu.setCurrentSelected(TvLeftMenuView.SEARCH_MENU)
                fragment = HomeFragment()
                actualFragment = SEARCH
            }

            TvLeftMenuView.SETTING_MENU -> {
                binding.rlLeftMenu.setCurrentSelected(TvLeftMenuView.SETTING_MENU)
                fragment = HomeFragment()
                actualFragment = REGISTER_REQUIRED
            }

            else -> {
                binding.rlLeftMenu.setCurrentSelected(TvLeftMenuView.HOME_MENU)
                fragment = HomeFragment()
                actualFragment = HOME
            }
        }
        leftMenusShown = false
        hideLeftMenu()
        Handler(Looper.getMainLooper()).postDelayed({
            replaceSelectedFragment(fragment)
        }, 100)

        Handler(Looper.getMainLooper()).postDelayed({
            setupFocusListener()
        }, 300)

    }

    private fun replaceSelectedFragment(fragment: Fragment?) {
        binding.browseFrameLayout.removeAllViewsInLayout()
        supportFragmentManager.beginTransaction().replace(R.id.browseFrameLayout, fragment!!)
            .disallowAddToBackStack()
//            .setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            .commit()
        if (leftMenusShown) {
            leftMenusShown = false
            hideLeftMenu()
        }
        binding.browseFrameLayout.requestFocus()
    }

    private fun setupFocusListener() {
        binding.rlLeftMenu.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.rlLeftMenu.onFocusChangeListener = null
                leftMenusShown = true
                showLeftMenu()
            } else {
                leftMenusShown = false
                hideLeftMenu()
            }
        }
    }

    fun requestMenuFocus() {
        binding.rlLeftMenu.requestFocus()
    }

    fun showLeftMenu() {
        val width = binding.rlLeftMenu.measuredWidth
        val valueAnimator = ValueAnimator.ofInt(width, maxExpandWidth)
        binding.rlLeftMenu.setupMenuExpandedUI()
        TvLeftMenuView.animateView(binding.rlLeftMenu, valueAnimator)
    }

    private fun hideLeftMenu() {
        val width = binding.rlLeftMenu.measuredWidth
        val valueAnimator = ValueAnimator.ofInt(width, minExpandWidth)
        binding.rlLeftMenu.setupMenuClosedUI()
        TvLeftMenuView.animateView(binding.rlLeftMenu, valueAnimator)
        leftMenusShown = false
    }

    fun hideMenu() {
        val width = binding.rlLeftMenu.measuredWidth
        val valueAnimator = ValueAnimator.ofInt(width, 0)
        binding.rlLeftMenu.setupMenuClosedUI()
        TvLeftMenuView.animateView(binding.rlLeftMenu, valueAnimator)
    }

    fun showMenu() {
        val width = binding.rlLeftMenu.measuredWidth
        val valueAnimator = ValueAnimator.ofInt(width, minExpandWidth)
        TvLeftMenuView.animateView(binding.rlLeftMenu, valueAnimator)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            when (actualFragment) {
                HOME -> {
                    val fragmentHome =
                        supportFragmentManager.findFragmentById(R.id.browseFrameLayout) as HomeFragment
                    return if (leftMenusShown) {
                        finishAffinity()
                        true
                    } else {
//                        if (fragmentHome.bannerVisible()) {
//                            fragmentHome.selectCarousel()
//                        }
                        showLeftMenu()
                        leftMenusShown = true
                        binding.rlLeftMenu.onFocusChangeListener = null
                        true
                    }
                }

                SETTINGS, MYSPACE, SEARCH, REGISTER_REQUIRED -> buttonBackPress()
                else -> return false
            }
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && leftMenusShown) {
            return resetLeftMenuUI()
        }
        return false
    }

    private fun resetLeftMenuUI(): Boolean {
        leftMenusShown = false
        binding.browseFrameLayout.requestFocus()
        hideLeftMenu()
        setupFocusListener()
        return true
    }

    override fun onDestroy() {
        binding.rlLeftMenu.onFocusChangeListener = null
        super.onDestroy()
    }

    private fun goToHome() {
        replaceSelectedFragment(HomeFragment())
        binding.rlLeftMenu.setCurrentSelected(TvLeftMenuView.HOME_MENU)
        actualFragment = HOME
    }

    private fun buttonBackPress(): Boolean {
        goToHome()
        return if (leftMenusShown) {
            setupFocusListener()
            true
        } else {
            resetLeftMenuUI()
            false
        }
    }

    companion object {

        const val HOME = 1
        const val SETTINGS = 2
        const val MYSPACE = 3
        const val SEARCH = 4
        const val REGISTER_REQUIRED = 5
    }
}
