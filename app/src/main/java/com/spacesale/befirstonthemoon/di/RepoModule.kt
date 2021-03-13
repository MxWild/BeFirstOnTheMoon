package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.view.details.PlanetDetailsRepository
import com.spacesale.befirstonthemoon.view.globe.GlobeRepository
import org.koin.dsl.module

val repoModule = module {
    single { PlanetDetailsRepository(db =get()) }
    single { GlobeRepository(db = get()) }
}