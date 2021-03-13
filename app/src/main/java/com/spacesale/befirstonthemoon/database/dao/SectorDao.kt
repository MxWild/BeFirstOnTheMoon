package com.spacesale.befirstonthemoon.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.database.entity.SectorEntity

@Dao
interface SectorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sectors: List<SectorEntity>)

    @Query("SELECT * FROM sector WHERE planetId = :planetId")
    fun getSectorsByPlanetId(planetId: Int): List<SectorEntity>

    @Query("SELECT * FROM sector WHERE planetId = :planetId AND sectorId = :sectorId")
    fun getSectorsByPlanetIdAndSectorId(planetId: Int, sectorId: Int): List<SectorEntity>

    @Query("DELETE FROM sector")
    fun clearAllSectors(): Int
}