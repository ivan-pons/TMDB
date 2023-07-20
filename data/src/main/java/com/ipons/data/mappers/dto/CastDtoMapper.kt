package com.ipons.data.mappers.dto

import com.ipons.domain.model.CastBO
import com.ipons.remotedatasource.dto.CastDto

fun CastDto.toCastBO(): CastBO = CastBO(
    castId = this.castId ?: -1,
    character = this.character ?: "",
    creditId = this.creditId ?: "",
    id = this.id ?: -1,
    name = this.name ?: "",
    order = this.order ?: 0,
    popularity = this.popularity ?: 0.0,
    profileImage = getImage(this.profilePath)
)

fun List<CastDto>?.toListCastBO(): List<CastBO> =
    this?.map { it.toCastBO() } ?: run { listOf() }
