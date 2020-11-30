package com.caesar84mx.shared.common.core.utils

import com.caesar84mx.shared.common.core.utils.StringResources.Language.ENGLISH
import com.caesar84mx.shared.common.core.utils.StringResources.Language.SPANISH
import com.caesar84mx.shared.common.core.utils.StringResources.Language.RUSSIAN

object StringResources {
    private val stringResource: Map<String, Map<Language, String>> = mapOf(
        Keys.Home.TITLE to mapOf(
            ENGLISH to "Hall Of Marvel Heroes",
            SPANISH to "Sala de Héroes Marvel",
            RUSSIAN to "Зал героев мира Марвела",
        ),
        Keys.Home.NAME_LABEL to mapOf(
            ENGLISH to "Name: ",
            SPANISH to "Nobmre: ",
            RUSSIAN to "Имя: ",
        ),
        Keys.Home.TEAM_LABEL to mapOf(
            ENGLISH to "Team: ",
            SPANISH to "Equipo: ",
            RUSSIAN to "Команда: ",
        ),
        Keys.Home.PUBLISHER_LABEL to mapOf(
            ENGLISH to "Publisher: ",
            SPANISH to "Editor: ",
            RUSSIAN to "Издатель: ",
        ),
        Keys.Home.EMPTY_LIST_MESSAGE to mapOf(
            ENGLISH to "Heroes list is empty.",
            SPANISH to "La lista de héroes está vacía.",
            RUSSIAN to "Список героев пуст.",
        ),
        Keys.Common.BUTTON_OK to mapOf(
            ENGLISH to "Ok",
            SPANISH to "De acuerdo",
            RUSSIAN to "Хорошо",
        ),
        Keys.Common.TITLE_ERROR to mapOf(
            ENGLISH to "Error",
            SPANISH to "Error",
            RUSSIAN to "Ошибка",
        )
    )

    fun getStringFor(id: String, language: Language = ENGLISH): String {
        stringResource[id]?.get(language)?.let { resource ->
            return resource
        }

        throw Exception("String resource $id not found in ${language.name}!")
    }

    object Keys {
        object Home {
            private const val HOME = "HomeScreen"
            const val TITLE = "$HOME.TITLE"
            const val EMPTY_LIST_MESSAGE = "$HOME.EMPTY_LIST_MESSAGE"
            const val NAME_LABEL = "$HOME.NAME_LABEL"
            const val TEAM_LABEL = "$HOME.TEAM_LABEL"
            const val PUBLISHER_LABEL = "$HOME.PUBLISHER_LABEL"
        }

        object Common {
            private const val COMMON = "Common"
            const val BUTTON_OK = "$COMMON.BUTTON_OK"
            const val TITLE_ERROR = "$COMMON.TITLE_ERROR"
        }
    }

    enum class Language {
        ENGLISH, SPANISH, RUSSIAN
    }
}