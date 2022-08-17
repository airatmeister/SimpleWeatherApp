package com.weather.weather.presentation.mapper

import com.weather.weather.domain.model.WeatherItem
import com.weatherapp.core.common.Mapper
import javax.inject.Inject

interface DomainToUiMapper :
    Mapper<WeatherItem, com.weather.weather.presentation.model.WeatherItem>
{
    class Base @Inject constructor(): DomainToUiMapper {
        override fun map(input: WeatherItem) =
            input.let { (city, weather) ->
                com.weather.weather.presentation.model.WeatherItem(city, weather)
            }
    }
}