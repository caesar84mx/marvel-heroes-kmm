package com.caesar84mx.shared.common

expect class Platform() {
    val platform: String
    val deviceId: String
}

expect var isNetworkAvailable: Boolean

internal expect fun printThrowable(t: Throwable)