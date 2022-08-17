package com.weather.favorite.presentation.adapter

import android.view.View
import android.view.ViewGroup
import com.weather.favorite.R
import com.weather.favorite.databinding.ItemSavedWeatherBinding
import com.weather.favorite.presentation.model.WeatherItem
import com.weatherapp.core.presentation.base.BaseViewHolder

class WeatherViewHolder(
    parent: ViewGroup,
    private val onClick: (String, String) -> Unit
): BaseViewHolder<ItemSavedWeatherBinding>(parent, R.layout.item_saved_weather) {

    override fun createBinding(itemView: View) = ItemSavedWeatherBinding.bind(itemView)

    fun bind(item: WeatherItem) = with(itemBinding) {
        textViewSavedCity.text = item.city
        textViewSavedWeather.text = item.weather
        buttonDelete.setOnClickListener { onClick.invoke(item.city, item.weather) }
    }
}