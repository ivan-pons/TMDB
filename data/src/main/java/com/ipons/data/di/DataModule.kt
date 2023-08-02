package com.ipons.data.di

import com.ipons.data.repositories.MovieRepository
import com.ipons.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {

    @Binds
    abstract fun bindMovieRepository(
        movieRepository: MovieRepository
    ): IMovieRepository


}