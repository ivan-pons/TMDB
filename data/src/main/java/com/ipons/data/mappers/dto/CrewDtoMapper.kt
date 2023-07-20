package com.ipons.data.mappers.dto

import com.ipons.domain.model.CrewBO
import com.ipons.remotedatasource.dto.CrewDto

fun CrewDto.toCrewBO(): CrewBO = CrewBO(
    id = this.id ?: -1,
    job = this.job ?: "",
    name = this.name ?: "",
    popularity = this.popularity ?: 0.0,
    profileImage = getImage(this.profilePath)
)

fun List<CrewDto>?.toListCrewBO(): List<CrewBO> =
    this?.map { it.toCrewBO() } ?: run { listOf() }