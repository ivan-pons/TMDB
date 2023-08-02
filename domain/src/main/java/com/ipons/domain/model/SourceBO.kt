package com.ipons.domain.model

import java.io.Serializable

data class SourceBO(
    val drmLicense: String = "",
    val manifestUrl: String = "https://bitdash-a.akamaihd.net/content/sintel/sintel.mpd",
    val thumbnailTrackUrl: String = ""
) : Serializable
