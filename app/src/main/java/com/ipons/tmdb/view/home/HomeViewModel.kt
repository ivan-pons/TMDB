package com.ipons.tmdb.view.home

import com.ipons.domain.usecase.GetHomeUseCase
import com.ipons.tmdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : BaseViewModel() {

    private val _status = Channel<HomeStatus>()
    val status = _status.receiveAsFlow()

    private val _actions = Channel<HomeActions>()
    val actions = _actions.receiveAsFlow()

    fun getHome() {
        executeUseCase(
            action = {
                val home = getHomeUseCase.execute(Unit)
                _status.send(HomeStatus.ShowHome(home))
            },
            exceptionHandler = {
                _status.send(HomeStatus.ShowHome(listOf()))
            }
        )
    }
}