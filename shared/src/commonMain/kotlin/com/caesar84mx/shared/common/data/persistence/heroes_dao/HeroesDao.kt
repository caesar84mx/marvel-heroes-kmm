package com.caesar84mx.shared.common.data.persistence.heroes_dao

import co.touchlab.kermit.Kermit
import com.caesar84mx.shared.common.core.extensions.asFlow
import com.caesar84mx.shared.common.core.extensions.mapToEntity
import com.caesar84mx.shared.common.core.extensions.mapToList
import com.caesar84mx.shared.common.data.networking.heroes.model.responses.HeroRaw
import com.caesar84mx.shared.common.data.persistence.common.AbstractDao
import com.otto.ottocastshared.db.HeroEntity
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class HeroesDao(
    sqlDriver: SqlDriver,
    log: Kermit,
    bkgDispatcher: CoroutineDispatcher
): AbstractDao(sqlDriver, log, bkgDispatcher) {
    fun saveHeroes(heroes: List<HeroRaw>) {
        heroes.forEach { hero ->
            db.heroEntityQueries.saveNew(
                name = hero.name,
                real_name = hero.realName,
                team = hero.team,
                first_appearance = hero.firstAppearance.toLong(),
                created_by = hero.createdBy,
                publisher = hero.publisher,
                avatar_url = hero.imageUrl,
                bio = hero.bio
            )
        }
    }

    fun findById(id: Long): Flow<HeroEntity> {
        log.i { "Finding hero id = $id" }
        return db.heroEntityQueries
            .get(id)
            .asFlow()
            .mapToEntity()
            .flowOn(bkgDispatcher)
    }

    fun findAll(): Flow<List<HeroEntity>> {
        log.i { "Getting heroes data from database" }
        return  db.heroEntityQueries
            .getAll()
            .asFlow()
            .mapToList()
            .flowOn(bkgDispatcher)
    }

    fun clear() = db.heroEntityQueries.clearAll()
}