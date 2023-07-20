package com.ipons.data.mappers.dto

import com.ipons.domain.model.CreditsBO
import com.ipons.remotedatasource.dto.CreditsDto

fun CreditsDto?.toCreditsBO(): CreditsBO = CreditsBO(
    cast = this?.cast.toListCastBO(),
    crew = this?.crew.toListCrewBO(),
    id = this?.id ?: -1
)