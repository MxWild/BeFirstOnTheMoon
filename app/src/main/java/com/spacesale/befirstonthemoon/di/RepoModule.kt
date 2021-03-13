package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.view.details.PlanetDetailsRepository
import org.koin.dsl.module

val repoModule = module {
    single { PlanetDetailsRepository(db =get()) }
}