package com.ipons.domain.model

data class CreditsBO(
    val cast: List<CastBO>,
    val crew: List<CrewBO>,
    val id: Int
)
