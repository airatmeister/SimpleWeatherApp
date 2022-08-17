package com.weatherapp.di.module

import com.weatherapp.core.di.module.ViewModelFactoryModule
import dagger.Module

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule