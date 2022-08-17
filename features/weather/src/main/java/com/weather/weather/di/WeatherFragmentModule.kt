package com.weather.weather.di

import com.weather.weather.presentation.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherFragmentModule {
    @ContributesAndroidInjector
    abstract fun provideWeatherFragment(): WeatherFragment
}