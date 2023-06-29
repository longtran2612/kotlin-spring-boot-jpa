package com.example.springoracledemo.domain.wrapper

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*
import java.util.concurrent.CompletableFuture
import java.util.function.Function

/**
 * 8:46 AM 02-Nov-22
 * Long Tran
 */

@Getter
@Setter
class WrapResponse<T> {
    @JsonProperty("Success")
    private var success = false

    @JsonProperty("Data")
    private var data: T? = null
    private var errorCode: String? = null
    private var message: MutableList<String?>? = null

    @JsonProperty("violations")
    private var violations: Boolean? = null
    private var refCode: String? = null

    @JsonProperty("StatusCode")
    private var statusCode: String? = null

    companion object {
        fun <T> error(msg: String?): WrapResponse<T> {
            val baseResponse: WrapResponse<T> = WrapResponse()
            baseResponse.data = null
            baseResponse.success = false
            baseResponse.statusCode = "500"
            baseResponse.message = mutableListOf(msg)
            return baseResponse
        }

        fun <T> ok(data: T?): WrapResponse<T> {
            val baseResponse: WrapResponse<T> = WrapResponse()
            baseResponse.data = data
            baseResponse.success = true
            baseResponse.statusCode = "200"
            return baseResponse
        }

        fun <T> okFuture(data: CompletableFuture<T?>?): CompletableFuture<WrapResponse<*>>? {
            if (data != null) {
                return data.thenApply(Function { data: T? -> ok(data) })
            }
            return null
        }

        @SneakyThrows
        fun <T> getClass(clazz: Class<T?>?): Any {
            if (clazz != null) {
                return ok<T?>(clazz.newInstance()).javaClass ?: WrapResponse::class.java
            }
            return WrapResponse::class.java
        }
    }
}
