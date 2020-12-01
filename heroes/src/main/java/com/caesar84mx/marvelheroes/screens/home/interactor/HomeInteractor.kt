package com.caesar84mx.marvelheroes.screens.home.interactor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.caesar84mx.marvelheroes.common.data.model.TaskResult
import com.caesar84mx.marvelheroes.screens.home.HeroesItemUIElement
import com.caesar84mx.marvelheroes.screens.home.toUIElement
import com.caesar84mx.shared.common.data.networking.heroes.HeroesApi
import com.caesar84mx.shared.common.data.networking.heroes.model.responses.HeroRaw
import com.caesar84mx.shared.common.data.repository.HeroesRepository
import com.caesar84mx.shared.common.viewmodels.HomeViewModel

class HomeInteractor(private val viewModel: HomeViewModel): ViewModel() {
    var requestStatus: TaskResult<List<HeroesItemUIElement>>? by mutableStateOf(null)
        private set

    fun loadData() {
        requestStatus = TaskResult.loading()
        viewModel.getHeroes { result ->
            val isSuccess = result.errorMessage == null
            if (isSuccess) {
                result.data?.let { heroes ->
                    requestStatus = TaskResult.success(
                        heroes.map { hero -> hero.toUIElement() }
                    )
                }
            } else {
                requestStatus = TaskResult.error(errorMessage = result.errorMessage)
            }
        }
    }

    companion object {
        val mock: HomeInteractor = HomeInteractor(
            HomeViewModel(
                api = object: HeroesApi {
                    override suspend fun getHeroes(): List<HeroRaw> = listOf()
                },
                repo = HeroesRepository.mock
            )
        ).apply {
            requestStatus = TaskResult.success(
                listOf(
                    HeroesItemUIElement(
                        name = "Captain America",
                        team = "Avengers",
                        publisher = "Marvel Comics",
                        avatar = "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg"
                    ),
                    HeroesItemUIElement(
                        name = "Iron Man",
                        team = "Avengers",
                        publisher = "Marvel Comics",
                        avatar = "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg"
                    ),
                    HeroesItemUIElement(
                        name = "Wolvarine",
                        team = "X-Men",
                        publisher = "Marvel Comics",
                        avatar = "https://www.simplifiedcoding.net/demos/marvel/logan.jpg"
                    )
                )
            )
        }
    }
}