package com.weatherapp.di.module

import android.app.Application
import android.content.Context
import com.weatherapp.App
import dagger.Module
import dagger.Provides

@Module(includes = [AppBindsModule::class])
class AppModule {

    @Provides
    fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    fun provideApplication(app: App): Application {
        return app
    }
}