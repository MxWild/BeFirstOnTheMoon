package com.spacesale.befirstonthemoon.view.details

import com.spacesale.befirstonthemoon.database.AppDatabase
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.domain.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlanetDetailsRepository(private val db: AppDatabase) {

    suspend fun getPlanetById(planetId: Int): Planet = withContext(Dispatchers.IO) {
         convertPlanetEntityToPlanet(db.planetDao().getById(planetId))
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