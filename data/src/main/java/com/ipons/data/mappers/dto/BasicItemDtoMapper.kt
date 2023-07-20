package com.ipons.data.mappers.dto

import com.ipons.domain.model.BasicItemBO
import com.ipons.remotedatasource.dto.BasicItemDto

fun BasicItemDto.toBasicItemBo(): BasicItemBO = BasicItemBO(
    backdropImage = getImage(this.backdropPath),
    id = this.id ?: -1,
    overview = this.overview ?: "",
    posterImage = getImage(this.posterPath),
    releaseDate = this.releaseDate ?: "",
    title = this.title ?: ""

)

fun List<BasicItemDto>?.toListBasicItemBO(): List<BasicItemBO> =
    this?.map { it.toBasicItemBo() } ?: run { listOf() }

