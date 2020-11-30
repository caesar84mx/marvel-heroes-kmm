package com.caesar84mx.shared.common.data.networking.common.api

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import com.caesar84mx.shared.common.core.extensions.endpoint
import com.caesar84mx.shared.common.core.extensions.protocol
import com.caesar84mx.shared.common.core.utils.exceptions.NoSessionException
import com.caesar84mx.shared.common.data.networking.common.model.Response
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalStdlibApi::class)
abstract class CommonApiClient(protected val log: Kermit) {
    protected val client: HttpClient = HttpClient {
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
                    log.i { ("[network]: $message") }
                }
            }

            level = LogLevel.ALL
        }

        defaultRequest {
            host = "www.simplifiedcoding.net"
            protocol = URLProtocol.HTTP
        }
    }

    init {
        ensureNeverFrozen()
    }
}