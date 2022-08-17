package com.weather.favorite.di

import com.weather.favorite.data.SavedCitiesRepositoryImpl
import com.weather.favorite.data.cache.CacheDataSource
import com.weather.favorite.data.mapper.DataToDomainMapper
import com.weather.favorite.domain.interactor.CitiesInteractor
import com.weather.favorite.domain.repository.SavedCitiesRepository
import com.weather.favorite.presentation.mapper.DomainToUiMapper
import dagger.Binds
import dagger.Module

@Module
abstract class FavoriteBindsModule {

    @Binds
    abstract fun bindDataToDomainMapper(dataToDomainMapper: DataToDomainMapper.Base): DataToDomainMapper

    @Binds
    abstract fun bindCacheDataSource(cacheDataSource: CacheDataSource.Base): CacheDataSource

    @Binds
    abstract fun bindSavedCitiesRepository(savedCitiesRepositoryImpl: SavedCitiesRepositoryImpl): SavedCitiesRepository

    @Binds
    abstract fun bindCitiesInteractor(citiesInteractor: CitiesInteractor.Base): CitiesInteractor

    @Binds
    abstract fun bindDomainToUiMapper(domainToUiMapper: DomainToUiMapper.Base): DomainToUiMapper
}