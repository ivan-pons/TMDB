package com.ipons.domain.usecase

import com.ipons.domain.base.UseCase
import com.ipons.domain.model.FullMovieBO
import com.ipons.domain.repository.IMovieRepository
import javax.inject.Inject

class GetFullMovieUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
): UseCase<Int, FullMovieBO>() {

    override suspend fun useCaseFunction(input: Int): FullMovieBO {
        return movieRepository.getFullMovie(input)
    }
}