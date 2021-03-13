package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.view.details.PlanetDetailsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    PlanetDetailsViewModel()
}