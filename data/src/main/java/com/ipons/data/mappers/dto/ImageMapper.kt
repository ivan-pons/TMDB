package com.ipons.data.mappers.dto

fun getImage(path: String?): String {
    return path?.let {
        "https://image.tmdb.org/t/p/original$path"
    } ?: run{
        ""
    }
}