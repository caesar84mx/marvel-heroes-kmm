package com.caesar84mx.shared.common.core.extensions

import co.touchlab.stately.freeze
import com.caesar84mx.shared.common.core.coroutines.IO_CONTEXT
import com.caesar84mx.shared.common.core.coroutines.MAIN_CONTEXT
import com.squareup.sqldelight.Query
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads

@JvmName("toFlow")
internal fun <T : Any> Query<T>.asFlow(): Flow<Query<T>> = flow {
    println("****** Converting to flow ******")

    emit(this@asFlow)

    val channel = Channel<Unit>(Channel.CONFLATED).freeze()
    val listener = object : Query.Listener {
        override fun queryResultsChanged() {
            channel.offer(Unit)
        }
    }

    addListener(listener)

    try {
        for (item in channel) {
            emit(this@asFlow)
        }
    } finally {
        println("****** Closing Flow in removeListener ******")
        removeListener(listener)
    }
}

@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToList(
    context: CoroutineContext = IO_CONTEXT
): Flow<List<T>> = map {
    withContext(context) {
        it.executeAsList()
    }
}

@JvmOverloads
internal fun <T : Any> Flow<Query<T>>.mapToEntity(
    context: CoroutineContext = IO_CONTEXT
): Flow<T> = map { query ->
    withContext(context) {
        val entity = query.executeAsOne()
        println("Mapping query to entity: $entity")
        entity
    }
}

internal fun runSyncedIo(mutex: Mutex, block: () -> Unit) {
    GlobalScope.launch {
        withContext(IO_CONTEXT) {
            mutex.withLock { block() }
        }
    }
}

internal fun runSyncedMain(mutex: Mutex, block: () -> Unit) {
    GlobalScope.launch {
        withContext(MAIN_CONTEXT) {
            mutex.withLock { block() }
        }
    }
}

expect fun launchIo(block: suspend CoroutineScope.() -> Unit)