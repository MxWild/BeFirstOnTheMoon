package com.spacesale.befirstonthemoon.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true)
    val purchaseId: Int? =0,
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "planetId")
    val planetId: Int,
    @ColumnInfo(name = "sectorId")
    val sectorId: Int
)
