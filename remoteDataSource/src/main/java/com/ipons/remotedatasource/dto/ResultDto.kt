package com.ipons.remotedatasource.dto
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ResultDto(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val results: List<FilmDto>?,
    @Json(name = "total_pages") val totalPages: Int?,
    @Json(name = "total_results") val totalResults: Int?
)
