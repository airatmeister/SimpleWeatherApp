package com.weather.weather.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.weather.weather.presentation.model.WeatherItem

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherItem>() {

    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem.city == newItem.city
    }

    override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem.weather == newItem.weather
    }

    override fun getChangePayload(oldItem: WeatherItem, newItem: WeatherItem): Any? {
        if (oldItem.city == newItem.city) return false
        return super.getChangePayload(oldItem, newItem)
    }
}