package com.caesar84mx.marvelheroes.screens.home.view

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.caesar84mx.marvelheroes.common.data.model.TaskResult
import com.caesar84mx.marvelheroes.common.theme.MarvelHeroesTheme
import com.caesar84mx.marvelheroes.screens.common.components.ErrorAlert
import com.caesar84mx.marvelheroes.screens.common.components.LoadingOverlay
import com.caesar84mx.marvelheroes.screens.home.HeroesItemUIElement
import com.caesar84mx.marvelheroes.screens.home.interactor.HomeInteractor
import com.caesar84mx.shared.common.core.extensions.notNull
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Home.EMPTY_LIST_MESSAGE
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Home.NAME_LABEL
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Home.PUBLISHER_LABEL
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Home.TEAM_LABEL
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Home.TITLE
import com.caesar84mx.shared.common.core.utils.StringResources.getStringFor
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun HomeScreenView(interactor: HomeInteractor) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = getStringFor(TITLE)) }
            )
        },
        bodyContent = { padding ->
            BodyView(
                padding = padding,
                heroes = interactor.requestStatus?.data.notNull()
            )
        }
    )

    interactor.requestStatus?.let { result ->
        when(result.status) {
            TaskResult.Status.LOADING -> LoadingOverlay()
            TaskResult.Status.ERROR -> ErrorAlert(result.errorMessage.notNull())
            TaskResult.Status.SUCCESS -> {  }
        }
    }
}

@Composable
private fun BodyView(
    padding: PaddingValues,
    heroes: List<HeroesItemUIElement>
) {
    if (heroes.isNotEmpty()) {
        ScrollableColumn(
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = padding.top, bottom = padding.bottom),
            modifier = Modifier.fillMaxSize()
        ) {
            heroes.forEach { hero ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CoilImage(
                            data = hero.avatar,
                            modifier = Modifier
                                .padding(3.dp)
                                .size(100.dp)
                        )
                        Column(
                            modifier = Modifier.padding(3.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 5.dp)
                            ) {
                                Text(
                                    text = getStringFor(NAME_LABEL),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = hero.name)
                            }

                            Row(
                                modifier = Modifier.padding(vertical = 5.dp)
                            ) {
                                Text(
                                    text = getStringFor(TEAM_LABEL),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = hero.team)
                            }

                            Row(
                                modifier = Modifier.padding(vertical = 5.dp)
                            ) {
                                Text(
                                    text = getStringFor(PUBLISHER_LABEL),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = hero.publisher)
                            }
                        }
                    }
                }
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = padding.top, bottom = padding.bottom)
                .fillMaxSize()
        ) {
            Text(
                text = getStringFor(EMPTY_LIST_MESSAGE),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenView_Preview() {
    MarvelHeroesTheme {
        HomeScreenView(interactor = HomeInteractor.mock)
    }
}