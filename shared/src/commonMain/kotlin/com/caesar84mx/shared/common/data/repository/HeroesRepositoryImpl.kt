package com.caesar84mx.shared.common.data.repository

import com.caesar84mx.shared.common.core.extensions.mapToHero
import com.caesar84mx.shared.common.data.model.Hero
import com.caesar84mx.shared.common.data.networking.heroes.model.responses.HeroRaw
import com.caesar84mx.shared.common.data.persistence.heroes_dao.HeroesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HeroesRepositoryImpl(private val dao: HeroesDao) : HeroesRepository {
    override fun saveHeroes(heroes: List<HeroRaw>) = dao.saveHeroes(heroes)

    override fun get(id: Long): Flow<Hero> =
        dao.findById(id).map { entity -> entity.mapToHero() }

    override fun findAll(): Flow<List<Hero>> =
        dao.findAll().map { entities -> entities.map { entity -> entity.mapToHero() } }

    override fun clear() = dao.clear()
}