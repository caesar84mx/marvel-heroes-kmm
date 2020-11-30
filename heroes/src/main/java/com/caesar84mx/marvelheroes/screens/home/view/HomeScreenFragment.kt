package com.caesar84mx.marvelheroes.screens.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.caesar84mx.marvelheroes.common.theme.MarvelHeroesTheme
import com.caesar84mx.marvelheroes.screens.home.interactor.HomeInteractor
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeScreenFragment: Fragment() {
    private val interactor: HomeInteractor by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interactor.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MarvelHeroesTheme {
                    HomeScreenView(interactor = interactor)
                }
            }
        }
    }
}