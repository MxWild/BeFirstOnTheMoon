package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.view.details.PlanetDetailsRepository
import com.spacesale.befirstonthemoon.view.planets.SelectPlanetRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        PlanetDetailsRepository(db =get())
        SelectPlanetRepository(db =get())
    }
}