package com.spacesale.befirstonthemoon.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey
    val planetId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "atmosphere")
    val atmosphere: String,
    @ColumnInfo(name = "characteristic")
    val characteristic: String,
    @ColumnInfo(name = "main_poster")
    val mainPoster: String,
    @ColumnInfo(name = "detail_poster")
    val detailPoster: String,
    @ColumnInfo(name = "texture")
    val texture: String
)