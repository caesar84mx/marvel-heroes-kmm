package com.caesar84mx.shared.common.di

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.caesar84mx.shared.db.MarvelHeroesDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.util.*
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(KtorExperimentalAPI::class)
actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            MarvelHeroesDb.Schema,
            get(),
            "heroes.db"
        )
    }

    single { Kermit(LogcatLogger()).withTag("Heroes.dev") }
}