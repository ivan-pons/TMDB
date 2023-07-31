package com.ipons.domain.model

data class ContainerBO(
    val id: Int,
    val title: String,
    val items: List<BasicItemBO>
)