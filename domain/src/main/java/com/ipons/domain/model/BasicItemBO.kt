package com.ipons.domain.model

import java.io.Serializable

data class BasicItemBO(
    val backdropImage: String,
    val id: Int,
    val overview: String,
    val posterImage: String,
    val releaseDate: String,
    val title: String,
) : Serializable
