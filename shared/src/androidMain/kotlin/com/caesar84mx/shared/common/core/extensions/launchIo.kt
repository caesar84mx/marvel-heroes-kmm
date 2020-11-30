package com.caesar84mx.shared.common.core.extensions

import com.caesar84mx.shared.common.core.coroutines.IO_CONTEXT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

actual fun launchIo(block: suspend CoroutineScope.() -> Unit) {
    GlobalScope.launch(IO_CONTEXT) { block() }
}