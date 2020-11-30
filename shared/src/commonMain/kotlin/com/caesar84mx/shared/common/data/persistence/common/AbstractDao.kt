package com.caesar84mx.shared.common.data.persistence.common

import co.touchlab.kermit.Kermit
import com.caesar84mx.shared.db.MarvelHeroesDb
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher

abstract class AbstractDao(
    sqlDriver: SqlDriver,
    protected val log: Kermit,
    protected val bkgDispatcher: CoroutineDispatcher
) {
    protected val db: MarvelHeroesDb = MarvelHeroesDb(sqlDriver)

}