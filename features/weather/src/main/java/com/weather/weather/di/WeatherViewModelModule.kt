package com.weather.weather.di

import androidx.lifecycle.ViewModel
import com.weather.weather.presentation.WeatherViewModel
import com.weatherapp.core.di.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeatherViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun weatherViewModel(viewModel: WeatherViewModel): ViewModel
}