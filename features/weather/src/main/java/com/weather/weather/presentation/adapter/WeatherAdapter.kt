package com.weather.weather.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.weather.weather.presentation.adapter.diffutil.WeatherDiffUtil
import com.weather.weather.presentation.model.WeatherItem
import javax.inject.Inject

class WeatherAdapter @Inject constructor() :
    ListAdapter<WeatherItem, WeatherViewHolder>(WeatherDiffUtil()) {

    var onClick: (String, String) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(parent, onClick)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}