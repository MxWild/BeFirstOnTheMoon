package com.spacesale.befirstonthemoon.domain

import androidx.annotation.DrawableRes

data class Planet (
    val id: Int,
    val name: String,
    @DrawableRes
    val mainPoster: Int,
    @DrawableRes
    val detailPoster: Int,
    val texture: String,
    val description: String,
    val atmosphere: String,
    val characteristics: String
)