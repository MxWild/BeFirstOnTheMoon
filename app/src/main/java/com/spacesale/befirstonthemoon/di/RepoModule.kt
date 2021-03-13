package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.repository.ProfileRepository
import org.koin.dsl.module

val repoModule = module {
    single { ProfileRepository(db = get()) }
}