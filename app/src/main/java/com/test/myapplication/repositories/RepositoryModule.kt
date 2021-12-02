package com.test.myapplication.repositories

import com.test.myapplication.networks.ApiService
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(
    appService: ApiService
) : MainRepository = MainRepository(appService)