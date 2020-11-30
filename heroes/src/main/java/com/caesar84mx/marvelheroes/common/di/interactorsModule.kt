package com.caesar84mx.marvelheroes.common.di

import com.caesar84mx.marvelheroes.screens.home.interactor.HomeInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val interactorsModule = module {
    viewModel { HomeInteractor(get()) }
}