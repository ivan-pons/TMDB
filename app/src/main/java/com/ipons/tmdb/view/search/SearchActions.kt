package com.ipons.tmdb.view.search

import com.ipons.domain.model.BasicItemBO

sealed class SearchActions {
    object OpenMenu : SearchActions()
    class OpenItem(val item: BasicItemBO) : SearchActions()
}