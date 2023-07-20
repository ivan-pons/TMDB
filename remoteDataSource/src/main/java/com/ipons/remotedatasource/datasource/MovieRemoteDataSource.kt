package com.ipons.remotedatasource.datasource

import com.ipons.remotedatasource.apis.CoreApi
import com.ipons.remotedatasource.dto.CreditsDto
import com.ipons.remotedatasource.dto.BasicItemDto
import com.ipons.remotedatasource.dto.FullMovieDto
import com.ipons.remotedatasource.extensions.parseResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val coreApi: CoreApi
) {

    suspend fun getPopularsMovies(): List<BasicItemDto>{
        return coreApi.getPopularFilms().parseResponse().results ?: listOf()
    }

    suspend fun getSimilarMovie(movieId: Int): List<BasicItemDto>{
        return coreApi.getSimilarMovie(movieId).parseResponse().results ?: listOf()
    }

    suspend fun searchMovie(searchText: String): List<BasicItemDto>{
        return coreApi.searchMovie(searchText).parseResponse().results ?: listOf()
    }

    suspend fun getFullMovie(movieId: Int): FullMovieDto {
        var movie = coreApi.getFullMovie(movieId).parseResponse()
        try {
            val creditsDto = getMovieCredits(movieId)
            movie = movie.copy(credits = creditsDto)
        } catch (_: Throwable){}
        return movie
    }

    private suspend fun getMovieCredits(movieId: Int): CreditsDto {
        return coreApi.getMovieCredits(movieId).parseResponse()
    }

}