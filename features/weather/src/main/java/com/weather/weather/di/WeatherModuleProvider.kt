package com.weather.weather.di

import dagger.Module

@Module(includes = [
    WeatherFragmentModule::class,
    WeatherViewModelModule::class,
    WeatherProvidesModule::class,
    WeatherBindsModule::class
])
abstract class WeatherModuleProvider