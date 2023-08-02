package com.ipons.tmdb.view.search.keyboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipons.tmdb.databinding.ItemKeyboardDeleteBinding
import com.ipons.tmdb.databinding.ItemKeyboardLetterBinding
import com.ipons.tmdb.databinding.ItemKeyboardSpaceBinding
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEYBOARD_DELETE_LAYOUT
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEYBOARD_LETTER_LAYOUT
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEYBOARD_SPACE_LAYOUT
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEY_DELETE_POSITION
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEY_SPACE_POSITION

class KeyboardAdapter(
    private val keySelected: (String) -> Unit,
    private val deleteSelected: () -> Unit,
    private val spaceSelected: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var initText = true

    private val keys = listOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "ñ",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "0"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            KEYBOARD_DELETE_LAYOUT -> KeyDeleteViewHolder(
                ItemKeyboardDeleteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            KEYBOARD_SPACE_LAYOUT -> KeySpaceViewHolder(
                ItemKeyboardSpaceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> KeyLetterViewHolder(
                ItemKeyboardLetterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = keys.size + 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            KEY_SPACE_POSITION -> KEYBOARD_SPACE_LAYOUT
            KEY_DELETE_POSITION -> KEYBOARD_DELETE_LAYOUT
            else -> KEYBOARD_LETTER_LAYOUT
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is KeyLetterViewHolder -> bindKeyLetterViewHolder(holder, position)
            is KeyDeleteViewHolder -> bindKeyDeleteViewHolder(holder)
            is KeySpaceViewHolder -> bindKeySpaceViewHolder(holder)
        }
    }

    private fun bindKeyDeleteViewHolder(holder: KeyDeleteViewHolder) {
        holder.binding.root.setOnClickListener {
            deleteSelected()
        }
    }

    private fun bindKeySpaceViewHolder(holder: KeySpaceViewHolder) {
        holder.binding.root.setOnClickListener {
            spaceSelected()
        }
    }

    private fun bindKeyLetterViewHolder(holder: KeyLetterViewHolder, position: Int) {
        if (initText) {
            holder.binding.letter.text = ""
            initText = false
        }

        holder.binding.letter.text = keys[position]

        holder.binding.root.setOnClickListener {
            keySelected(keys[position])
        }
    }

    class KeyLetterViewHolder internal constructor(val binding: ItemKeyboardLetterBinding) :
        RecyclerView.ViewHolder(binding.root)

    class KeySpaceViewHolder internal constructor(val binding: ItemKeyboardSpaceBinding) :
        RecyclerView.ViewHolder(binding.root)

    class KeyDeleteViewHolder internal constructor(val binding: ItemKeyboardDeleteBinding) :
        RecyclerView.ViewHolder(binding.root)
}