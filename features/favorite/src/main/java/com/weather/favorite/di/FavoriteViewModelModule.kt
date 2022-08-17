package com.weather.favorite.di

import androidx.lifecycle.ViewModel
import com.weather.favorite.presentation.FavoriteViewModel
import com.weatherapp.core.di.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun favoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}