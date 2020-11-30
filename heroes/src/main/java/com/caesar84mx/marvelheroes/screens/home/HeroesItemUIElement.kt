package com.caesar84mx.marvelheroes.screens.home

import com.caesar84mx.shared.common.data.model.Hero

data class HeroesItemUIElement(
    val name: String,
    val team: String,
    val publisher: String,
    val avatar: String
)

fun Hero.toUIElement(): HeroesItemUIElement = HeroesItemUIElement(
    name = name,
    team = team,
    publisher = publisher,
    avatar = avatar
)