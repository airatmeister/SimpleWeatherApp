package com.weatherapp.di.component

import com.weatherapp.App
import com.weatherapp.di.module.ActivityModule
import com.weatherapp.di.module.AppModule
import com.weatherapp.di.module.DbModule
import com.weatherapp.di.module.features.FavoriteModule
import com.weatherapp.di.module.features.WeatherModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    WeatherModule::class,
    FavoriteModule::class,
    DbModule::class
])
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: App): AppComponent
    }
}