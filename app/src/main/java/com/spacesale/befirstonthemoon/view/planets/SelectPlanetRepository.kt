package com.spacesale.befirstonthemoon.view.planets

import com.spacesale.befirstonthemoon.database.AppDatabase
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.domain.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SelectPlanetRepository(private val db: AppDatabase) {

    suspend fun getPlanets(): Map<Int, Planet> = withContext(Dispatchers.IO) {
        val map: MutableMap<Int, Planet> = mutableMapOf()
         db.planetDao().getAll().forEach {
             val planet = convertPlanetEntityToPlanet(it)
             map.put(planet.id, planet)
         }
        return@withContext map
    }

    private fun convertPlanetEntityToPlanet(planetEntity: PlanetEntity) = Planet(
        id = planetEntity.planetId,
        name = planetEntity.name,
        mainPoster = planetEntity.mainPoster,
        detailPoster = planetEntity.detailPoster,
        texture = planetEntity.texture,
        description = planetEntity.description,
        atmosphere = planetEntity.atmosphere,
        characteristics = planetEntity.characteristic
    )
}