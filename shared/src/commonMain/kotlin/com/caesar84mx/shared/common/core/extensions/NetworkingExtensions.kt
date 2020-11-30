package com.caesar84mx.shared.common.core.extensions

import io.ktor.client.request.*
import io.ktor.http.*

var HttpRequestBuilder.endpoint: String
    get() = url.encodedPath
    set(value) {
        url.encodedPath = value
    }

var HttpRequestBuilder.protocol: URLProtocol
    get() = url.protocol
    set(value) {
        url.protocol = value
    }