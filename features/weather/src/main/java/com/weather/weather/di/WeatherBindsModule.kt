package com.weather.weather.di

import com.weather.weather.data.WeatherRepositoryImpl
import com.weather.weather.data.cache.CacheDataSource
import com.weather.weather.data.mapper.DataToDomainMapper
import com.weather.weather.domain.interactors.WeatherInteractor
import com.weather.weather.domain.repository.WeatherRepository
import com.weather.weather.presentation.mapper.DomainToUiMapper
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherBindsModule {

    @Binds
    abstract fun bindWeatherInteractor(weatherInteractor: WeatherInteractor.Base): WeatherInteractor

    @Binds
    abstract fun bindDomainToUiMapper(mapper: DomainToUiMapper.Base): DomainToUiMapper

    @Binds
    abstract fun bindDataToDomainMapper(mapper: DataToDomainMapper.Base): DataToDomainMapper

    @Binds
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindCacheDataSource(cacheDataSource: CacheDataSource.Base): CacheDataSource
}