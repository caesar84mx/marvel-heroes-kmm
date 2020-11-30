package com.caesar84mx.marvelheroes.common.data.model

class TaskResult<T: Any> private constructor(
    val status: Status,
    val data: T? = null,
    val errorMessage: String? = null
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T: Any> success(data: T?): TaskResult<T> {
            return TaskResult(Status.SUCCESS, data)
        }
        fun <T: Any> error(errorMessage: String?): TaskResult<T> {
            return TaskResult(Status.ERROR, null, errorMessage)
        }
        fun <T: Any> loading(): TaskResult<T> {
            return TaskResult(Status.LOADING)
        }
    }
}