package com.ipons.tmdb.view.home

import com.ipons.domain.model.ContainerBO

sealed class HomeStatus {
    class ShowHome(val home: List<ContainerBO>) : HomeStatus()
}