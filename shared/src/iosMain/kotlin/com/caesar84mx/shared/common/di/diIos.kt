package com.caesar84mx.shared.common.di

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.Logger
import co.touchlab.kermit.PlatformThrowableStringProvider
import co.touchlab.kermit.Severity
import com.caesar84mx.shared.common.core.extensions.protocol
import com.caesar84mx.shared.db.MarvelHeroesDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import kotlinx.serialization.json.Json
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import platform.Foundation.NSLog
import platform.Foundation.NSURLCredential
import platform.Foundation.create
import platform.Foundation.serverTrust

actual val platformModule: Module = module {
    single { client }
    single<SqlDriver> { NativeSqliteDriver(MarvelHeroesDb.Schema, "heroes.db") }
    single { Kermit(NSLogLogger()).withTag("Heroes.dev") }
}

val client = HttpClient(Ios) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Logging) {
        logger = object : io.ktor.client.features.logging.Logger {
            override fun log(message: String) {
                NSLog("[network]: %s", message)
            }
        }

        level = LogLevel.ALL
    }

    defaultRequest {
        host = "www.simplifiedcoding.net"
        protocol = URLProtocol.HTTP
    }
}

class NSLogLogger : Logger() {
    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        NSLog("%s: (%s) %s", severity.name, tag, message)
        throwable?.let {
            val string = PlatformThrowableStringProvider().getThrowableString(it)
            NSLog("%s", string)
        }
    }
}

fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier, null)
}