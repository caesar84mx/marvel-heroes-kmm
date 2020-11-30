package com.caesar84mx.marvelheroes.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightThemeColors = lightColors(
    primary = ThemeBlue,
    onPrimary = Color.White,
    secondary = XGray,
    onSecondary = Color.Gray,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Red800,
    onError = Color.White
)

val DarkThemeColors = darkColors(
    primary = DarkTheme,
    onPrimary = Color.Black,
    secondary = Color.DarkGray,
    onSecondary = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    error = Red300,
    onError = Color.Black
)

@Composable
val Colors.snackbarAction: Color
    get() = if (isLight) DarkTheme else ThemeBlue

@Composable
val Colors.onSecondaryVariant: Color
    get() = if (isLight) Color.Blue else Color.White


@Composable
fun MarvelHeroesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightThemeColors
    }
    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}