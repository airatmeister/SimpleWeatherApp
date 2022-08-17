package com.weather.weather.data.mapper

import com.weather.weather.domain.model.WeatherItem
import com.weatherapp.core.common.Mapper
import javax.inject.Inject

interface DataToDomainMapper: Mapper<Pair<String, Float>, WeatherItem> {

    class Base @Inject constructor(): DataToDomainMapper {
        override fun map(input: Pair<String, Float>): WeatherItem {
            return input.let { (city, weather) ->
                WeatherItem(city, weather.toString())
            }
        }
    }
}