package com.spacesale.befirstonthemoon.repository

import com.spacesale.befirstonthemoon.database.AppDatabase
import com.spacesale.befirstonthemoon.domain.Purchase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val db: AppDatabase) {

    suspend fun getPurchasesByUser(userId: Int): List<Purchase> {
        val purchases: MutableList<Purchase> = mutableListOf()
        var planetsIds: List<Int> = emptyList()
        return withContext(Dispatchers.IO) {
            val purchaseEntities = db.userDao().getAllPurchase(userId)

            //id планет
            planetsIds = purchaseEntities.map {
                it.planetId
            }

            planetsIds.distinct().forEach { planetId ->
                val planetEntity = db.planetDao().getPlanetById(planetId)
                var sectors = ""
                sectors = purchaseEntities
                    .filter { it.planetId == planetId }
                    .map { "Участок " + it.sectorId }
                    .joinToString("\n")

                purchases.add(
                    Purchase(
                        planetPicture = planetEntity.detailPoster,
                        planetName = planetEntity.name,
                        sectorNames = sectors
                    )
                )
            }
            purchases
        }
    }

}