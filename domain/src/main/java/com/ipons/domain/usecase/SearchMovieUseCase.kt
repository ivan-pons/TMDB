package com.ipons.domain.usecase

import com.ipons.domain.base.UseCase
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.repository.IMovieRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
): UseCase<String, List<BasicItemBO>>() {

    override suspend fun useCaseFunction(input: String): List<BasicItemBO> {
       return movieRepository.search(input)
    }
}