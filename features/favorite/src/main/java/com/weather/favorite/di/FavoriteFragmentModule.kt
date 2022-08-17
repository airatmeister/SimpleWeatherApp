package com.weather.favorite.di

import com.weather.favorite.presentation.FavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentModule {
    @ContributesAndroidInjector
    abstract fun provideFavoriteFragment(): FavoriteFragment
}