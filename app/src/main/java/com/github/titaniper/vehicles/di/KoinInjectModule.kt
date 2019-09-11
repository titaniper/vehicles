package com.github.titaniper.vehicles.di

import org.koin.dsl.module.module

val retrofitPart = module {}

val adapterPart = module {}

val modelPart = module {}

val viewModelPart = module {}

val koinInjectModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)