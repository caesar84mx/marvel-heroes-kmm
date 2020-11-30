package com.caesar84mx.shared.common.core.extensions

fun String?.notNull(): String = this ?: ""
fun Boolean?.notNull(): Boolean = this ?: false
fun Int?.notNull(): Int = this ?: 0
fun Long?.notNull(): Long = this ?: 0
fun Float?.notNull(): Float = this ?: 0f
fun Double?.notNull(): Double = this ?: 0.0