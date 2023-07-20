package com.ipons.domain.model

data class CastBO(
    val castId: Int,
    val character: String,
    val creditId: String,
    val id: Int,
    val name: String,
    val order: Int,
    val popularity: Double,
    val profilePath: String
)