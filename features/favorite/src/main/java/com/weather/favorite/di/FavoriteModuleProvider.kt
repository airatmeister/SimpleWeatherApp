package com.weather.favorite.di

import dagger.Module

@Module(includes = [
    FavoriteFragmentModule::class,
    FavoriteViewModelModule::class,
    FavoriteBindsModule::class
])
abstract class FavoriteModuleProvider