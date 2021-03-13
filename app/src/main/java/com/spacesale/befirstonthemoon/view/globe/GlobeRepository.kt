package com.spacesale.befirstonthemoon.view.globe

import com.spacesale.befirstonthemoon.database.AppDatabase
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.domain.Planet

class GlobeRepository(private val db: AppDatabase) {

    fun getPlanetById(planetId: Int): Planet {
        return convertPlanetEntityToPlanet(db.planetDao().getById(planetId))
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