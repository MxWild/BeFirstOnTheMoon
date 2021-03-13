package com.spacesale.befirstonthemoon.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.spacesale.befirstonthemoon.database.entity.SectorEntity

@Dao
interface SectorDao {

    @Query("SELECT * FROM SECTOR WHERE planetId = :planetId")
    fun getSectorsByPlanetId(planetId: Int): List<SectorEntity>

    @Query("DELETE FROM sector")
    fun clearAllSectors(): Int
}