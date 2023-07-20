package com.ipons.remotedatasource.datasource

import com.ipons.remotedatasource.apis.CoreApi
import com.ipons.remotedatasource.dto.CreditsDto
import com.ipons.remotedatasource.dto.FilmDto
import com.ipons.remotedatasource.dto.FullMovieDto
import com.ipons.remotedatasource.extensions.parseResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val coreApi: CoreApi
) {

    suspend fun getPopularsMovies(): List<FilmDto>{
        return coreApi.getPopularFilms().parseResponse().results ?: listOf()
    }

    suspend fun getSimilarMovie(movieId: Int): List<FilmDto>{
        return coreApi.getSimilarMovie(movieId).parseResponse().results ?: listOf()
    }

    suspend fun searchMovie(searchText: String): List<FilmDto>{
        return coreApi.searchMovie(searchText).parseResponse().results ?: listOf()
    }

    suspend fun getFullMovie(movieId: Int): FullMovieDto {
        return coreApi.getFullMovie(movieId).parseResponse()
    }

    suspend fun getMovieCredits(movieId: Int): CreditsDto {
        return coreApi.getMovieCredits(movieId).parseResponse()
    }

}