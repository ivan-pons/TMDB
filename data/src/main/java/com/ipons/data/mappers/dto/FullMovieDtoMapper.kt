package com.ipons.data.mappers.dto

import com.ipons.domain.model.CreditsBO
import com.ipons.domain.model.FullMovieBO
import com.ipons.domain.model.GenreBO
import com.ipons.domain.model.ProductionCompanyBO
import com.ipons.domain.model.ProductionCountryBO
import com.ipons.domain.model.SpokenLanguageBO
import com.ipons.remotedatasource.dto.FullMovieDto

fun FullMovieDto.toFullMovieBO() : FullMovieBO  = FullMovieBO(
     adult = this.adult ?: false,
 backdropImage = getImage(this.backdropPath),
 budget = this.budget ?: 0,
 genres = listOf(), //fixme
 homepage = this.homepage ?: "",
 id = this.id ?: -1,
 imdbId = this.imdbId ?: "",
 description = this.overview ?: "",
 popularity = this.popularity ?: 0.0,
 posterImage = getImage(this.posterPath),
 productionCompanies = listOf(), //fixme
 productionCountries = listOf(), //fixme
 releaseDate = this.releaseDate ?: "",
 revenue = this.revenue ?: 0,
 runtime = this.revenue ?: 0,
 spokenLanguages = listOf(), //fixme
 tagline = this.tagline ?: "",
 title = this.title ?: "",
 voteAverage = this.voteAverage ?: 0.0,
 voteCount = this.revenue ?: 0,
 credits = CreditsBO(listOf(), listOf(),-1) //FIXME
)