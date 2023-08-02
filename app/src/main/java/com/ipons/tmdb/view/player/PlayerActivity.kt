package com.ipons.tmdb.view.player

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.ipons.tmdb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerActivity : FragmentActivity() {

    private val playerViewModel by viewModels<PlayerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }
}