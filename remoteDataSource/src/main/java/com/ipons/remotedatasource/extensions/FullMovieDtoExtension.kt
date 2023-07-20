package com.ipons.remotedatasource.extensions

import com.ipons.remotedatasource.dto.CreditsDto
import com.ipons.remotedatasource.dto.FullMovieDto
import com.ipons.remotedatasource.dto.GenreDto
import com.ipons.remotedatasource.dto.ProductionCompanyDto
import com.ipons.remotedatasource.dto.ProductionCountryDto
import com.ipons.remotedatasource.dto.SpokenLanguageDto

fun FullMovieDto.copy(
     adult: Boolean? = null,
     backdropPath: String? = null,
     budget: Int? = null,
     genres: List<GenreDto>? = null,
     homepage: String? = null,
     id: Int? = null,
     imdbId: String? = null,
     originalLanguage: String? = null,
     originalTitle: String? = null,
     overview: String? = null,
     popularity: Double? = null,
     posterPath: String? = null,
     productionCompanies: List<ProductionCompanyDto>? = null,
     productionCountries: List<ProductionCountryDto>? = null,
     releaseDate: String? = null,
     revenue: Int? = null,
     runtime: Int? = null,
     spokenLanguages: List<SpokenLanguageDto>? = null,
     status: String? = null,
     tagline: String? = null,
     title: String? = null,
     video: Boolean? = null,
     voteAverage: Double? = null,
     voteCount: Int? = null,
     credits: CreditsDto? = null
): FullMovieDto = FullMovieDto(
    adult ?: this.adult,
    backdropPath ?: this.backdropPath,
    budget ?: this.budget,
    genres ?: this.genres,
    homepage ?: this.homepage,
    id ?: this.id,
    imdbId ?: this.imdbId,
    originalLanguage ?: this.originalLanguage,
    originalTitle ?: this.originalTitle,
    overview ?: this.overview,
    popularity ?: this.popularity,
    posterPath ?: this.posterPath,
    productionCompanies ?: this.productionCompanies,
    productionCountries ?: this.productionCountries,
    releaseDate ?: this.releaseDate,
    revenue ?: this.revenue,
    runtime ?: this.runtime,
    spokenLanguages ?: this.spokenLanguages,
    status ?: this.status,
    tagline ?: this.tagline,
    title ?: this.title,
    video ?: this.video,
    voteAverage ?: this.voteAverage,
    voteCount ?: this.voteCount,
    credits ?: this.credits,
)