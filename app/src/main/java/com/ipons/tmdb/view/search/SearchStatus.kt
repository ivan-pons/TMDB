package com.ipons.tmdb.view.search

import com.ipons.domain.model.BasicItemBO

sealed class SearchStatus {
    class ShowText(val text: String) : SearchStatus()
    class ShowSuggestions(val suggestions: List<String>) : SearchStatus()
    class ShowSearch(val items: List<BasicItemBO>) : SearchStatus()
    object ShowLoading : SearchStatus()
    object HideLoading : SearchStatus()
}