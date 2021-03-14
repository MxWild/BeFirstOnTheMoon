package com.spacesale.befirstonthemoon.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spacesale.befirstonthemoon.database.entity.PurchaseEntity
import com.spacesale.befirstonthemoon.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insert(user: UserEntity)

    @Insert
    suspend fun insertPurchase(purchase: PurchaseEntity)

    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserById(userId: Int): UserEntity

    @Query("DELETE FROM users")
    fun clearAllUsers(): Int

    @Query("SELECT * FROM purchase WHERE userId = :userId")
    fun getAllPurchase(userId: Int): List<PurchaseEntity>

    @Query("SELECT * FROM purchase WHERE planetId = :planetId AND sectorId = :sectorId")
    fun getPurchaseByPlanetAndSector(planetId: Int, sectorId: Int): List<PurchaseEntity>

}