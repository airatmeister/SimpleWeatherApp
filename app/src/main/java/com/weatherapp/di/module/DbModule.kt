package com.weatherapp.di.module

import android.content.Context
import androidx.room.Room
import com.weatherapp.core.data.db.AppDatabase
import com.weatherapp.core.data.db.CityDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase{
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "com.weather.appDb"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCityDao(db: AppDatabase): CityDao{
        return db.cityDao()
    }
}