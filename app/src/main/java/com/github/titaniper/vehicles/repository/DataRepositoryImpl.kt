package com.github.titaniper.vehicles.repository

import com.github.titaniper.vehicles.model.response.LoginResponse
import com.github.titaniper.vehicles.model.response.Response
import com.github.titaniper.vehicles.model.service.VehicleService
import io.reactivex.Single

class DataRepositoryImpl(private val service: VehicleService): DataRepository {
    override fun auth(id: String, password: String): Single<Response<LoginResponse>> {
        return service.login(id, password, "android")
    }
}