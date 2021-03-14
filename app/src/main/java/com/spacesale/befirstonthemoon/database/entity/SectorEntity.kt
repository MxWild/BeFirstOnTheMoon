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
    val wkt: String,
    @ColumnInfo(name = "LAYER")
    val layer: String,
    @ColumnInfo(name = "COUNTRY_NA")
    val countryNa: String,
    //ID участка
    @ColumnInfo(name = "ID")
    val id: Int,
    //ID соседнего участка
    @ColumnInfo(name = "FAC_ID")
    val facId: Int
)