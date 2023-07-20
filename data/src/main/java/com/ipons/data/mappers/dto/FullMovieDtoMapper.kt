package com.ipons.data.mappers.dto

import com.ipons.domain.model.FullMovieBO
import com.ipons.remotedatasource.dto.FullMovieDto

fun FullMovieDto.toFullMovieBO() : FullMovieBO  = FullMovieBO(
 adult = this.adult ?: false,
 backdropImage = getImage(this.backdropPath),
 budget = this.budget ?: 0,
 genres = this.genres.toListGenreBO(),
 homepage = this.homepage ?: "",
 id = this.id ?: -1,
 imdbId = this.imdbId ?: "",
 description = this.overview ?: "",
 popularity = this.popularity ?: 0.0,
 posterImage = getImage(this.posterPath),
 productionCompanies = this.productionCompanies.toListProductionCompanyBO(),
 productionCountries = this.productionCountries.toListProductionCountryBO(),
 releaseDate = this.releaseDate ?: "",
 revenue = this.revenue ?: 0,
 runtime = this.revenue ?: 0,
 spokenLanguages = this.spokenLanguages.toListSpokenLanguageBO(),
 tagline = this.tagline ?: "",
 title = this.title ?: "",
 voteAverage = this.voteAverage ?: 0.0,
 voteCount = this.revenue ?: 0,
 credits = this.credits.toCreditsBO()
)

