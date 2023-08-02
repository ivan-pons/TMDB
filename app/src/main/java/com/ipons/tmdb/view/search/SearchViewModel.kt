package com.ipons.tmdb.view.search

import androidx.lifecycle.viewModelScope
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.usecase.SearchMovieUseCase
import com.ipons.tmdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : BaseViewModel() {

    private val _status = Channel<SearchStatus>()
    val status = _status.receiveAsFlow()

    private val _actions = Channel<SearchActions>()
    val actions = _actions.receiveAsFlow()

    private var searchText = ""

    fun viewCreated() {
        doSearch("")
    }

    fun deleteSelected() {
        searchText = searchText.dropLast(1)
        getSuggestions(searchText)
        doSearch(searchText)
        showSearchText()
    }

    fun spaceSelected() {
        searchText = "$searchText "
        showSearchText()
    }

    fun suggestionSelected(suggestion: String) {
        keySelected(suggestion, "")
    }

    fun itemSelected(item: BasicItemBO) {
        viewModelScope.launch {
            _actions.send(SearchActions.OpenItem(item))
        }
    }

    fun keySelected(allText: String, key: String) {
        searchText = "$allText$key"
        getSuggestions(searchText)
        doSearch(searchText)
        showSearchText()
    }

    private fun getSuggestions(searchText: String) {
        executeUseCase(
            action = {

            },
            exceptionHandler = {

            }
        )
    }

    private fun doSearch(searchText: String) {
        executeUseCase(
            action = {
                _status.send(SearchStatus.ShowLoading)
                val result = searchMovieUseCase.execute(searchText)

                _status.send(SearchStatus.ShowSearch(result))
                _status.send(SearchStatus.HideLoading)
            },
            exceptionHandler = {
                // Do nothing
            }
        )
    }

    fun menuSelected() {
        viewModelScope.launch {
            _actions.send(SearchActions.OpenMenu)
        }
    }

    private fun showSearchText() {
        viewModelScope.launch {
            _status.send(SearchStatus.ShowText(searchText))
        }
    }

}