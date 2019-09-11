package com.github.titaniper.vehicles.repository

import com.github.titaniper.vehicles.model.response.LoginResponse
import com.github.titaniper.vehicles.model.response.Response
import io.reactivex.Single

interface DataRepository {
    fun auth(id: String, password: String): Single<Response<LoginResponse>>
}