package com.caesar84mx.shared.common.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val IO_CONTEXT: CoroutineContext
    get() = Dispatchers.Default

actual val MAIN_CONTEXT: CoroutineContext
    get() = Dispatchers.Main

actual val bkgDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default