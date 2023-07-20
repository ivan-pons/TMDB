package com.ipons.data.repositories

import com.ipons.data.mappers.dto.toFullMovieBO
import com.ipons.data.mappers.dto.toListBasicItemBO
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.model.FullMovieBO
import com.ipons.domain.repository.IMovieRepository
import com.ipons.remotedatasource.datasource.MovieRemoteDataSource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
): IMovieRepository {

    override suspend fun getPopulars(): List<BasicItemBO> {
        return movieRemoteDataSource.getPopularsMovies().toListBasicItemBO()
    }

    override suspend fun search(searchText: String): List<BasicItemBO> {
        return movieRemoteDataSource.searchMovie(searchText).toListBasicItemBO()
    }

    override suspend fun getSimilar(movieId: Int): List<BasicItemBO> {
        return movieRemoteDataSource.getSimilarMovie(movieId).toListBasicItemBO()
    }

    override suspend fun getFullMovie(movieId: Int): FullMovieBO {
        return movieRemoteDataSource.getFullMovie(movieId).toFullMovieBO()
    }

}