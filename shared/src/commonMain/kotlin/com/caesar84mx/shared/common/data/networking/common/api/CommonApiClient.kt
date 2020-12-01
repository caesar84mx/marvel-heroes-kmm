package com.caesar84mx.shared.common.data.networking.common.api

import co.touchlab.kermit.Kermit
import co.touchlab.stately.ensureNeverFrozen
import io.ktor.client.*

@OptIn(ExperimentalStdlibApi::class)
abstract class CommonApiClient(protected val log: Kermit, protected val client: HttpClient) {
    init {
        ensureNeverFrozen()
    }
}