package com.spacesale.befirstonthemoon.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
data class PurchaseEntity(
    @PrimaryKey
    val purchaseId: Int,
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "planetId")
    val planetId: Int,
    @ColumnInfo(name = "sectorId")
    val sectorId: Int
)
