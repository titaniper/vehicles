package com.github.titaniper.vehicles.model.response

data class Response<T> (
    var error:String?,
    var data: T?
)