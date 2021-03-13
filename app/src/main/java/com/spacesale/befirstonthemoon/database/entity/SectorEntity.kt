package com.spacesale.befirstonthemoon.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sector")
data class SectorEntity(
    //Это обычный инкрементальный ключ
    @PrimaryKey(autoGenerate = true)
    val sectorId: Int = 0,
    //ID планеты
    @ColumnInfo(name = "planetId")
    val planetId: Int,
    @ColumnInfo(name = "isSale")
    val isSale: Boolean,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "WKT")
    val WKT: String,
    @ColumnInfo(name = "LAYER")
    val LAYER: String,
    @ColumnInfo(name = "COUNTRY_NA")
    val COUNTRY_NA: String,
    //ID участка
    @ColumnInfo(name = "ID")
    val ID: Int,
    //ID соседнего участка
    @ColumnInfo(name = "FAC_ID")
    val FAC_ID: Int
)