package com.ipons.tmdb.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ipons.domain.model.BasicItemBO
import com.ipons.tmdb.R
import com.ipons.tmdb.base.BaseFragment
import com.ipons.tmdb.databinding.SearchFragmentBinding
import com.ipons.tmdb.extensions.launchAndCollect
import com.ipons.tmdb.view.search.keyboard.KeyboardAdapter
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEY_DELETE_POSITION
import com.ipons.tmdb.view.search.keyboard.KeyboardConstants.KEY_SPACE_POSITION
import com.ipons.tmdb.view.search.sugestions.SuggestionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel by viewModels<SearchViewModel>()

    private var adapter: SearchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKeyboard()
        startAnimation()
        initStatus()
        initActions()
        searchViewModel.viewCreated()
    }

    override fun onResume() {
        super.onResume()
        binding.rvKeyboard.requestFocus()
    }

    private fun initStatus() {
        this.launchAndCollect(searchViewModel.status) { status ->
            when (status) {
                SearchStatus.ShowLoading -> changeLoadingVisibility(true)
                SearchStatus.HideLoading -> changeLoadingVisibility(false)
                is SearchStatus.ShowText -> showText(status.text)
                is SearchStatus.ShowSuggestions -> showSuggestions(status.suggestions)
                is SearchStatus.ShowSearch -> showSearch(status.items)
            }
        }
    }

    private fun initActions() {
        this.launchAndCollect(searchViewModel.actions) { actions ->
            when (actions) {
                SearchActions.OpenMenu -> openMenu()
                is SearchActions.OpenItem -> openItem(actions.item)
            }
        }
    }

    private fun initKeyboard() = binding.run {

        val keyboardAdapterTV = KeyboardAdapter(keySelected = { key: String ->
            searchViewModel.keySelected(tvSearch.text.toString(), key)
        }, deleteSelected = {
            searchViewModel.deleteSelected()
        }, spaceSelected = {
            searchViewModel.spaceSelected()
        })

        val layoutManager = rvKeyboard.layoutManager as GridLayoutManager
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    KEY_SPACE_POSITION -> 3
                    KEY_DELETE_POSITION -> 2
                    else -> 1
                }
            }
        }

        rvKeyboard.adapter = keyboardAdapterTV
    }

    private fun showText(text: String) {
        binding.tvSearch.text = text
    }

    private fun showSuggestions(suggestions: List<String>) {
        binding.rvSuggestion.adapter = SuggestionAdapter(suggestions) { suggestion ->
            searchViewModel.suggestionSelected(suggestion)
        }
    }

    private fun showSearch(items: List<BasicItemBO>) {
        adapter = SearchAdapter(items)

        binding.rvSearch.adapter = adapter

    }

    private fun startAnimation() {
        binding.cursor.startAnimation(getCursorAnimation())

        binding.tvSearch.addTextChangedListener {
            if (it.toString().isEmpty()) {
                binding.twSearchHint.setHint(R.string.search_hint)
            } else {
                binding.twSearchHint.hint = ""
            }
        }

    }

    private fun getCursorAnimation(): Animation {
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 50
        anim.startOffset = 500
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        return anim
    }

    private fun changeLoadingVisibility(isVisible: Boolean) {
        binding.loading.isVisible = isVisible
    }

    private fun openItem(searchItem: BasicItemBO) {
        //Navigate Information
    }
}