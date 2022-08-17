package com.weather.favorite.presentation

import android.os.Bundle
import android.widget.Toast
import com.weather.favorite.R
import com.weather.favorite.databinding.FragmentFavoriteBinding
import com.weather.favorite.presentation.adapter.WeatherAdapter
import com.weatherapp.core.plugins.viewbinding.viewBinding
import com.weatherapp.core.presentation.base.BaseFragment
import javax.inject.Inject

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by settingsViewModels()

    @Inject lateinit var weatherAdapter: WeatherAdapter

    override fun callOperations() = Unit

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        weatherAdapter.onClick = { city, _ ->
            viewModel.deleteCity(city)
            Toast.makeText(requireContext().applicationContext, "Deleted", Toast.LENGTH_LONG).show()
        }
        recyclerSavedWeather.adapter = weatherAdapter
    }

    override fun onBindViewModel() = with(viewModel) {
        savedCities.observe {
            weatherAdapter.submitList(it)
        }
    }
}