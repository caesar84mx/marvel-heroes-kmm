package com.caesar84mx.shared.common.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

expect val IO_CONTEXT: CoroutineContext
expect val MAIN_CONTEXT: CoroutineContext
expect val bkgDispatcher: CoroutineDispatcher