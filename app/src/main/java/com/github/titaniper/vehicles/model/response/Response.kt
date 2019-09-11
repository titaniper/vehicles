package com.github.titaniper.vehicles.model.response

import java.util.*

data class Response<T> (
    var error:String?,
    var data: T?
)