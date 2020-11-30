package com.caesar84mx.shared.common.core.extensions

inline fun <E> List<E>?.notNull(): List<E> = this ?: listOf()