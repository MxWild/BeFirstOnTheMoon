package com.spacesale.befirstonthemoon.view.globe

import com.spacesale.befirstonthemoon.database.AppDatabase
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.database.entity.PurchaseEntity
import com.spacesale.befirstonthemoon.database.entity.SectorEntity
import com.spacesale.befirstonthemoon.domain.Planet
import com.spacesale.befirstonthemoon.domain.Sector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GlobeRepository(private val db: AppDatabase) {

    suspend fun getPlanetById(planetId: Int): Planet = withContext(Dispatchers.IO){
        convertPlanetEntityToPlanet(db.planetDao().getById(planetId))
    }

    suspend fun getSectorByPlanetId(planetId: Int): List<Sector> = withContext(Dispatchers.IO) {
        db.sectorDao().getSectorsByPlanetId(planetId).map {
            convertSectorEntityToSector(it)
        }
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

    suspend fun buySector(planetId: Int,sectorId: Int): Boolean = withContext(Dispatchers.IO) {
        if (db.userDao().getPurchaseByPlanetAndSector(planetId, sectorId).count() == 0) {
            db.sectorDao().buySector(planetId, sectorId)
            db.userDao().insertPurchase(PurchaseEntity(null, 1, planetId, sectorId))
            return@withContext true
        } else {
            return@withContext false
        }
    }

    private fun convertSectorEntityToSector(sectorEntity: SectorEntity) = Sector(
        sectorId = sectorEntity.id,
        isSale = sectorEntity.isSale,
        price = sectorEntity.price,
        wkt = sectorEntity.wkt,
        layer = sectorEntity.layer,
        countryNa = sectorEntity.countryNa
    )

}