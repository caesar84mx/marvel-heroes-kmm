package com.caesar84mx.shared.common.data.networking.heroes

import co.touchlab.kermit.Kermit
import com.caesar84mx.shared.common.core.extensions.endpoint
import com.caesar84mx.shared.common.data.networking.common.api.CommonApiClient
import com.caesar84mx.shared.common.data.networking.heroes.HeroesApi.Companion.HEROES
import com.caesar84mx.shared.common.data.networking.heroes.model.responses.HeroRaw
import io.ktor.client.*
import io.ktor.client.request.*
import kotlin.collections.get

class HeroesApiImpl(kermit: Kermit, client: HttpClient) : CommonApiClient(kermit, client), HeroesApi {
    override suspend fun getHeroes(): List<HeroRaw> {
        log.i { "Performing get request" }
        return client.get {
            url.encodedPath = HEROES
        }
    }
}