package com.spacesale.befirstonthemoon.domain

data class Planet (
    val id: Int,
    val name: String,
    val mainPoster: String,
    val detailPoster: String,
    val texture: String,
    val description: String,
    val atmosphere: String,
    val characteristics: String
)