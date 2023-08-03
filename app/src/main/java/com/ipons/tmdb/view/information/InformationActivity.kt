package com.ipons.tmdb.view.information

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.ipons.domain.model.BasicItemBO
import com.ipons.tmdb.databinding.ActivityInformationBinding
import com.ipons.tmdb.extensions.serializable
import com.ipons.tmdb.view.player.PlayerActivity

class InformationActivity : FragmentActivity() {

    private lateinit var binding: ActivityInformationBinding
    private val informationViewModel by viewModels<InformationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.serializable<BasicItemBO>("ITEM_CLICKED")?.let {
            bind(it)
        } ?: run {
            val toast = Toast.makeText(applicationContext, "Error Inesperado", Toast.LENGTH_SHORT)
            toast.show()
            finish()
        }
    }


    fun bind(item: BasicItemBO) {
        binding.item = item

        binding.btnPlay.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }
}