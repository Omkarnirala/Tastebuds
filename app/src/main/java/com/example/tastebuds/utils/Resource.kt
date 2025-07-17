package com.example.tastebuds.utils

data class Resource<out out>(val status: Status, val data: out?, val message: String?) {

    companion object {

        fun <out> success(data: out): Resource<out> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <out> errorBody(data: out?, message: String): Resource<out> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <out> loading(data: out?): Resource<out> =
            Resource(status = Status.LOADING, data = data, message = null)
    }
}