package com.ipons.tmdb.view.information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.ipons.tmdb.R

class InformationActivity : FragmentActivity() {

    private val informationViewModel by viewModels<InformationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
    }
}