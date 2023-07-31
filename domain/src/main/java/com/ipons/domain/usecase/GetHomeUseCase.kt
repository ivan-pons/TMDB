package com.ipons.domain.usecase

import com.ipons.domain.base.UseCase
import com.ipons.domain.model.ContainerBO
import com.ipons.domain.repository.IMovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
) : UseCase<Unit, List<ContainerBO>>() {

    override suspend fun useCaseFunction(input: Unit): List<ContainerBO> {

        return coroutineScope {
            val popularsDeferred = async { getPopulars() }
            val nowDeferred = async { getNowPlayingFilms() }
            val topDeferred = async { getTopFilms() }
            val upcomingDeferred = async { getUpcomingFilms() }

            val home =
                listOf(popularsDeferred, nowDeferred, topDeferred, upcomingDeferred).awaitAll()
            return@coroutineScope home.filterNotNull()
        }
    }

    private suspend fun getPopulars(): ContainerBO? {
        return try {
            val populars = movieRepository.getPopulars()
            ContainerBO(1, "Populares", populars)
        } catch (t: Throwable) {
            null
        }
    }

    private suspend fun getTopFilms(): ContainerBO? {
        return try {
            val top = movieRepository.getTopFilms()
            ContainerBO(2, "Más Valorados", top)
        } catch (t: Throwable) {
            null
        }
    }

    private suspend fun getUpcomingFilms(): ContainerBO? {
        return try {
            val upcoming = movieRepository.getUpcomingFilms()
            ContainerBO(3, "Proximamente", upcoming)
        } catch (t: Throwable) {
            null
        }
    }

    private suspend fun getNowPlayingFilms(): ContainerBO? {
        return try {
            val now = movieRepository.getNowPlayingFilms()
            ContainerBO(4, "En Cartelera", now)
        } catch (t: Throwable) {
            null
        }
    }
}