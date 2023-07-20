package com.ipons.data.mappers.dto

import com.ipons.domain.model.GenreBO
import com.ipons.remotedatasource.dto.GenreDto

fun GenreDto.toGenreBO(): GenreBO = GenreBO(
    id = this.id ?: -1,
    name = this.name ?: ""
)

fun List<GenreDto>?.toListGenreBO(): List<GenreBO> =
    this?.map { it.toGenreBO() } ?: run { listOf() }