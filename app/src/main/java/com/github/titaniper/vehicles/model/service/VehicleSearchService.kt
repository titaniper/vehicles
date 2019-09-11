package com.github.titaniper.vehicles.model.service

import com.github.titaniper.vehicles.model.VehicleInfo
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

    @GET("/mobile/v1/users/self/vehicles/")
    fun vehicles(@Header("Authorization") token: String): Single<Response<List<VehicleInfo>>>

    @FormUrlEncoded
    @PUT("/mobile/v1/users/self/vehicles/{vehicle}/favorite")
    fun favorite(
            @Header("Authorization") token: String,
            @Path("vehicle") vehicle: String,
            @Field("status") status: Boolean
    ): Single<Response<String>>
}