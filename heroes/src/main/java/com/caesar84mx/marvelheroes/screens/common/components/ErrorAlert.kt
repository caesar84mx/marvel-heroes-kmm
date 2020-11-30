package com.caesar84mx.marvelheroes.screens.common.components

import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Common.BUTTON_OK
import com.caesar84mx.shared.common.core.utils.StringResources.Keys.Common.TITLE_ERROR
import com.caesar84mx.shared.common.core.utils.StringResources.getStringFor

@Composable
fun ErrorAlert(message: String) {
    val show = remember { mutableStateOf(true) }
    if (show.value) {
        AlertDialog(
            onDismissRequest = { show.value = false },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth()
                ) {
                    Button(
                        onClick = { show.value = false },
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text(
                            text = getStringFor(BUTTON_OK),
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            },
            title = { Text(getStringFor(TITLE_ERROR)) },
            text = { Text(message) }
        )
    }
}