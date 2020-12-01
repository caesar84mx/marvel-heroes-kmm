package com.caesar84mx.shared.common.data.networking.heroes

import com.caesar84mx.shared.common.data.networking.heroes.model.responses.HeroRaw

interface HeroesApi {
    suspend fun getHeroes(): List<HeroRaw>

    companion object {
        const val HEROES = "demos/marvel/"
    }
}