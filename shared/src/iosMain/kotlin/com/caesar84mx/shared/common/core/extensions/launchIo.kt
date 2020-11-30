package com.caesar84mx.shared.common.core.extensions

import co.touchlab.kermit.Kermit
import com.caesar84mx.shared.common.core.coroutines.MainScope
import com.caesar84mx.shared.common.di.NSLogLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val scope = MainScope(Dispatchers.Main, Kermit(NSLogLogger()).withTag("Otto.dev"))

actual fun launchIo(block: suspend CoroutineScope.() -> Unit) {
    scope.launch { block() }
}