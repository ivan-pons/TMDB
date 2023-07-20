package com.ipons.domain.usecase

import com.ipons.domain.base.UseCase
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.repository.IMovieRepository
import javax.inject.Inject

class GetSimilarMoviesUseCase  @Inject constructor(
    private val movieRepository: IMovieRepository
): UseCase<Int, List<BasicItemBO>>() {

    override suspend fun useCaseFunction(input: Int): List<BasicItemBO> {
        return movieRepository.getSimilar(input)
    }
}