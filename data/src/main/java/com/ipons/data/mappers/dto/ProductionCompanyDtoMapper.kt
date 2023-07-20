package com.ipons.data.mappers.dto

import com.ipons.domain.model.ProductionCompanyBO
import com.ipons.remotedatasource.dto.ProductionCompanyDto

fun ProductionCompanyDto.toProductionCompanyBO(): ProductionCompanyBO = ProductionCompanyBO(
    id = this.id ?: -1,
    logoImage = getImage(this.logoPath),
    name = this.name ?: "",
    originCountry = this.originCountry ?: ""
)

fun List<ProductionCompanyDto>?.toListProductionCompanyBO(): List<ProductionCompanyBO> =
    this?.map { it.toProductionCompanyBO() } ?: run { listOf() }