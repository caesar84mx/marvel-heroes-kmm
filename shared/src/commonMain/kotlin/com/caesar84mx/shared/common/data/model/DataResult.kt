package com.caesar84mx.shared.common.data.model

data class DataResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
)