package com.caesar84mx.shared.common.core.coroutines

import com.caesar84mx.shared.common.printThrowable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ModelScope(context: CoroutineContext): CoroutineScope {
    private val job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        printThrowable(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = MAIN_CONTEXT + job + exceptionHandler

    fun onDestroy() {
        job.cancel()
    }
}