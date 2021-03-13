package com.spacesale.befirstonthemoon.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity

@Dao
interface PlanetDao {

    @Query("SELECT Count(*) FROM planets")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets where planetId=:planetId")
    suspend fun getPlanetById(planetId: Int): PlanetEntity

    @Query("DELETE FROM planets")
    fun clearAllPlanet(): Int

    @Query("SELECT * FROM planets WHERE planetId = :planetId")
    fun getById(planetId: Int): PlanetEntity

}