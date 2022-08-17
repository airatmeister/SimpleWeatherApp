package com.weather.weather.presentation.adapter

import android.view.View
import android.view.ViewGroup
import com.weather.weather.R
import com.weather.weather.databinding.ItemWeatherBinding
import com.weather.weather.presentation.model.WeatherItem
import com.weatherapp.core.presentation.base.BaseViewHolder

class WeatherViewHolder(
    parent: ViewGroup,
    private val onClick: (String, String) -> Unit
): BaseViewHolder<ItemWeatherBinding>(parent, R.layout.item_weather) {

    override fun createBinding(itemView: View) = ItemWeatherBinding.bind(itemView)

    fun bind(item: WeatherItem) = with(itemBinding) {
        textViewCity.text = item.city
        textViewWeather.text = item.weather
        buttonFavorite.setOnClickListener { onClick.invoke(item.city, item.weather) }
    }
}