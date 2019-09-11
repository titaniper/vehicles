package com.github.titaniper.vehicles.repository

import com.github.titaniper.vehicles.model.VehicleInfo
import com.github.titaniper.vehicles.model.response.Response
import com.github.titaniper.vehicles.model.response.LoginResponse
import io.reactivex.Single

interface DataRepository {
    fun auth(id: String, password: String): Single<Response<LoginResponse>>
    fun getData(query:String, token:String): Single<Response<List<VehicleInfo>>>
    fun favorite(token: String, id: String, status: Boolean): Single<Response<String>>
}