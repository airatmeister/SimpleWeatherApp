package com.weatherapp.di.module

import com.weatherapp.core.common.Dispatchers
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindsModule {

    @Binds
    abstract fun bindDispatchers(dispatchers: Dispatchers.Base): Dispatchers
}