package com.weather.favorite.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.weather.favorite.presentation.adapter.diffutil.WeatherDiffUtil
import com.weather.favorite.presentation.model.WeatherItem
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