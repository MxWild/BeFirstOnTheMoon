package com.spacesale.befirstonthemoon.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(planets: List<PlanetEntity>)

    @Query("DELETE FROM planets")
    fun clearAllPlanet(): Int

}