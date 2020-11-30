package com.caesar84mx.marvelheroes.common.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.caesar84mx.shared.common.appContentResolver
import com.caesar84mx.marvelheroes.common.di.interactorsModule
import com.caesar84mx.shared.common.di.initKoin
import org.koin.dsl.module

class MarvelHeroesApp: Application() {
    private val systemDiModule = module {
        single<Context> { this@MarvelHeroesApp }
        single<SharedPreferences> {
            get<Context>().getSharedPreferences("MarvelHeroesSettings", Context.MODE_PRIVATE)
        }
    }

    override fun onCreate() {
        super.onCreate()

        initKoin(listOf(systemDiModule, interactorsModule))
        appContentResolver = contentResolver
    }
}