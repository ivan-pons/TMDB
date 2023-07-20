package com.ipons.domain.model

data class FullMovieBO(
    val adult: Boolean,
    val backdropImage: String,
    val budget: Int,
    val genres: List<GenreBO>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val description: String,
    val popularity: Double,
    val posterImage: String,
    val productionCompanies: List<ProductionCompanyBO>,
    val productionCountries: List<ProductionCountryBO>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageBO>,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val credits: CreditsBO
)
