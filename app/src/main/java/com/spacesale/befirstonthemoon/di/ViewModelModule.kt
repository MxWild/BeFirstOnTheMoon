package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.view.details.PlanetDetailsViewModel
import com.spacesale.befirstonthemoon.view.globe.GlobeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlanetDetailsViewModel(repository = get()) }
    viewModel { GlobeViewModel(globeRepository = get()) }
}