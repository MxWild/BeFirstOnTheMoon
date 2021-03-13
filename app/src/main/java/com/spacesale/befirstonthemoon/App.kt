package com.spacesale.befirstonthemoon

import android.app.Application
import com.spacesale.befirstonthemoon.di.databaseModule
import com.spacesale.befirstonthemoon.di.repoModule
import com.spacesale.befirstonthemoon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(databaseModule, repoModule, viewModelModule))
        }
    }

}