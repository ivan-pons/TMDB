package com.ipons.data.mappers.dto

import com.ipons.domain.model.SpokenLanguageBO
import com.ipons.remotedatasource.dto.SpokenLanguageDto

fun SpokenLanguageDto.toSpokenLanguageBO(): SpokenLanguageBO = SpokenLanguageBO(
    englishName = this.englishName ?: "",
    name = this.name ?: ""
)

fun List<SpokenLanguageDto>?.toListSpokenLanguageBO(): List<SpokenLanguageBO> =
    this?.map { it.toSpokenLanguageBO() } ?: run { listOf() }