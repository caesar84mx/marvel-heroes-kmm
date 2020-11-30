package com.caesar84mx.shared.common.data.networking.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val status: Int,
    val result: T? = null,
    val message: String? = null
)