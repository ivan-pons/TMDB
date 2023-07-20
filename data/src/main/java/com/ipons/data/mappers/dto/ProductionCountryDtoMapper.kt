package com.ipons.data.mappers.dto

import com.ipons.domain.model.ProductionCountryBO
import com.ipons.remotedatasource.dto.ProductionCountryDto

fun ProductionCountryDto.toProductionCountryBO(): ProductionCountryBO = ProductionCountryBO(
    name ?: ""
)

fun List<ProductionCountryDto>?.toListProductionCountryBO(): List<ProductionCountryBO> =
    this?.map { it.toProductionCountryBO() } ?: run { listOf() }