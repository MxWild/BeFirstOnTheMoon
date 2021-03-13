package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.view.details.PlanetDetailsViewModel
import com.spacesale.befirstonthemoon.view.profile.ProfileViewModel
import com.spacesale.befirstonthemoon.view.globe.GlobeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProfileViewModel(profileRepository = get()) }
    viewModel { PlanetDetailsViewModel(repository = get()) }
    viewModel { GlobeViewModel(repository = get()) }
}