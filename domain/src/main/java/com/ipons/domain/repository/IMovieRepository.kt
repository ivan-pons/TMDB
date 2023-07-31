package com.ipons.domain.repository

import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.model.FullMovieBO

interface IMovieRepository {
    suspend fun getPopulars(): List<BasicItemBO>
    suspend fun getTopFilms(): List<BasicItemBO>
    suspend fun getUpcomingFilms(): List<BasicItemBO>
    suspend fun getNowPlayingFilms(): List<BasicItemBO>
    suspend fun search(searchText: String): List<BasicItemBO>
    suspend fun getSimilar(movieId: Int): List<BasicItemBO>
    suspend fun getFullMovie(movieId: Int): FullMovieBO
}