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
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import platform.Foundation.*

actual val platformModule: Module = module {
    single<SqlDriver> { NativeSqliteDriver(MarvelHeroesDb.Schema, "heroes.db") }
    single { Kermit(NSLogLogger()).withTag("Heroes.dev") }
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

lateinit var userDefaults: NSUserDefaults

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