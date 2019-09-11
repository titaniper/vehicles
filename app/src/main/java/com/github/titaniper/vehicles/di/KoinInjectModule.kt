package com.github.titaniper.vehicles.di

import com.github.titaniper.vehicles.Constants
import com.github.titaniper.vehicles.model.service.VehicleService
import com.github.titaniper.vehicles.repository.DataRepository
import com.github.titaniper.vehicles.repository.DataRepositoryImpl
import com.github.titaniper.vehicles.view.login.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitPart = module {
    single<VehicleService> {
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(VehicleService::class.java)
    }
}
val adapterPart = module {}

val modelPart = module {
    factory<DataRepository> {
        DataRepositoryImpl(get())
    }
}

val viewModelPart = module {
    viewModel {
        LoginViewModel(get())
    }
}

val koinInjectModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)