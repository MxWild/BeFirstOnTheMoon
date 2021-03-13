package com.spacesale.befirstonthemoon.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sector")
data class SectorEntity(
    @PrimaryKey
    val sectorId: Int,
    @ColumnInfo(name = "planetId")
    val planetId: Int,
    @ColumnInfo(name = "isSale")
    val isSale: Boolean,
    @ColumnInfo(name = "price")
    val price: Double
)