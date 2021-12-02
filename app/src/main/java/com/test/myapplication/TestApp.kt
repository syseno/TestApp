package com.test.myapplication

import android.app.Application
import com.test.myapplication.networks.networkModule
import com.test.myapplication.repositories.repositoryModule
import com.test.myapplication.viewmodels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}