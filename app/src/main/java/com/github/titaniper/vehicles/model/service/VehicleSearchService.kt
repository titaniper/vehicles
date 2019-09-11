package com.github.titaniper.vehicles.model.service

import com.github.titaniper.vehicles.model.response.Response
import com.github.titaniper.vehicles.model.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.*

interface VehicleService {
    @FormUrlEncoded
    @POST("/mobile/v1/auth/")
    fun login(
        @Field("userId") userId: String,
        @Field("password") password: String,
        @Field("deviceType") deviceType: String
    ): Single<Response<LoginResponse>>
}