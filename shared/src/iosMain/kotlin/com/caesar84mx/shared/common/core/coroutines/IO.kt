package com.caesar84mx.shared.common.core.coroutines

import co.touchlab.kermit.Kermit
import com.caesar84mx.shared.common.printThrowable
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

actual val IO_CONTEXT: CoroutineContext
    get() = Dispatchers.Main
actual val MAIN_CONTEXT: CoroutineContext
    get() = Dispatchers.Main

actual val bkgDispatcher: CoroutineDispatcher
    get() = Dispatchers.Main

class MainScope(private val mainContext: CoroutineContext, private val log: Kermit) :
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler

    internal val job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        printThrowable(throwable)
        showError(throwable)
    }

    // TODO: Some way of exposing this to the caller without trapping a reference and freezing it.
    private fun showError(t: Throwable) {
        log.e(throwable = t) { "Error in MainScope" }
    }

    fun onDestroy() {
        job.cancel()
    }
}
