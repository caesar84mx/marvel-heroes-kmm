package com.caesar84mx.shared.common.di

import android.util.Log
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.caesar84mx.shared.common.core.extensions.protocol
import com.caesar84mx.shared.db.MarvelHeroesDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(KtorExperimentalAPI::class)
actual val platformModule: Module = module {
    single { client }
    single<SqlDriver> {
        AndroidSqliteDriver(
            MarvelHeroesDb.Schema,
            get(),
            "heroes.db"
        )
    }

    single { Kermit(LogcatLogger()).withTag("Heroes.dev") }
}

@KtorExperimentalAPI
val client: HttpClient = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("[network]: ", message)
            }
        }

        level = LogLevel.ALL
    }

    defaultRequest {
        host = "www.simplifiedcoding.net"
        protocol = URLProtocol.HTTP
    }
}