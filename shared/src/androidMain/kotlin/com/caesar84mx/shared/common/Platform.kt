package com.caesar84mx.shared.common

import android.content.ContentResolver
import android.provider.Settings

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val deviceId: String = Settings.Secure.getString(appContentResolver, Settings.Secure.ANDROID_ID)
}

actual var isNetworkAvailable: Boolean = true

internal actual fun printThrowable(t: Throwable) {
    t.printStackTrace()
}

lateinit var appContentResolver: ContentResolver
