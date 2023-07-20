package com.ipons.domain.usecase

import com.ipons.domain.base.UseCase
import com.ipons.domain.model.BasicItemBO
import com.ipons.domain.repository.IMovieRepository
import javax.inject.Inject

class GetPopularsUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
): UseCase<Unit, List<BasicItemBO>>() {

    override suspend fun useCaseFunction(input: Unit): List<BasicItemBO> {
        return movieRepository.getPopulars()
    }
}