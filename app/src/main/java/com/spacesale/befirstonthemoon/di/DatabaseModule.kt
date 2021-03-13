package com.spacesale.befirstonthemoon.di

import com.spacesale.befirstonthemoon.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase(context = get()) }
    single(createdAtStart = false) { get<AppDatabase>().sectorDao()  }
    single(createdAtStart = false) { get<AppDatabase>().planetDao()  }
}