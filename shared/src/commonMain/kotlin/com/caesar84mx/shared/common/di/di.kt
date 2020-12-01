package com.caesar84mx.shared.common.di

import com.caesar84mx.shared.common.Platform
import com.caesar84mx.shared.common.core.coroutines.bkgDispatcher
import com.caesar84mx.shared.common.data.networking.heroes.HeroesApi
import com.caesar84mx.shared.common.data.networking.heroes.HeroesApiImpl
import com.caesar84mx.shared.common.data.persistence.heroes_dao.HeroesDao
import com.caesar84mx.shared.common.data.repository.HeroesRepository
import com.caesar84mx.shared.common.data.repository.HeroesRepositoryImpl
import com.caesar84mx.shared.common.viewmodels.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModules: List<Module> = listOf()): KoinApplication {
    val allModules = mutableListOf(coreModule, platformModule)
    allModules.addAll(appModules)

    return startKoin {
        modules(allModules)
    }
}

private val coreModule = module {
    single { Platform() }
    single<HeroesApi> { HeroesApiImpl(get(), get()) }
    single { HeroesDao(get(), get(), bkgDispatcher) }
    single<HeroesRepository> { HeroesRepositoryImpl(get()) }
    single { HomeViewModel(get(), get()) }
}

expect val platformModule: Module