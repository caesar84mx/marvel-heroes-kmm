package com.caesar84mx.shared.common.viewmodels

import com.caesar84mx.shared.common.core.coroutines.IO_CONTEXT
import com.caesar84mx.shared.common.core.coroutines.MAIN_CONTEXT
import com.caesar84mx.shared.common.core.extensions.launchIo
import com.caesar84mx.shared.common.data.model.DataResult
import com.caesar84mx.shared.common.data.model.Hero
import com.caesar84mx.shared.common.data.networking.heroes.HeroesApi
import com.caesar84mx.shared.common.data.repository.HeroesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

class HomeViewModel(private val api: HeroesApi, private val repo: HeroesRepository): KoinComponent {
    fun getHeroes(onProcessed: (DataResult<List<Hero>>) -> Unit) {
        launchIo {
            try {
                val rawHeroes = api.getHeroes()
                repo.clear()
                repo.saveHeroes(rawHeroes)
            } catch (error: Throwable) {
                val errorMessage = error.message ?: "An error occurred"
                onProcessed(DataResult(errorMessage = errorMessage))
            } finally {
                repo.findAll()
                    .collect { heroes ->
                        if (heroes.isNotEmpty()) {
                            onProcessed(DataResult(heroes))
                        }
                    }
            }
        }
    }
}