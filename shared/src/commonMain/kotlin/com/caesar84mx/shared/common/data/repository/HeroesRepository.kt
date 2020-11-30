package com.caesar84mx.shared.common.data.repository

import com.caesar84mx.shared.common.data.model.Hero
import com.caesar84mx.shared.common.data.networking.heroes.model.responses.HeroRaw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface HeroesRepository {
    fun saveHeroes(heroes: List<HeroRaw>)

    fun get(id: Long): Flow<Hero>

    fun findAll(): Flow<List<Hero>>

    fun clear()

    companion object {
        val mock = object : HeroesRepository {
            override fun saveHeroes(heroes: List<HeroRaw>) {}

            override fun get(id: Long): Flow<Hero> = flow { Hero() }

            override fun findAll(): Flow<List<Hero>> = flow { listOf<Hero>() }

            override fun clear() { }
        }
    }
}