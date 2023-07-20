package com.ipons.remotedatasource.dto
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class CreditsDto(
    @Json(name = "cast") val cast: List<CastDto>?,
    @Json(name = "crew") val crew: List<CrewDto>?,
    @Json(name = "id") val id: Int?
)