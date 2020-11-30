package com.caesar84mx.shared.common

import com.caesar84mx.shared.common.core.extensions.notNull
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val deviceId: String = UIDevice.currentDevice.identifierForVendor?.UUIDString().notNull()
}

actual var isNetworkAvailable: Boolean = true

internal actual fun printThrowable(t: Throwable) {
    t.printStackTrace()
}