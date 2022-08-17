package com.weather.favorite.presentation.mapper

import com.weather.favorite.domain.model.City
import com.weather.favorite.presentation.model.WeatherItem
import com.weatherapp.core.common.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DomainToUiMapper : Mapper<Flow<List<City>>, Flow<List<WeatherItem>>> {

    class Base @Inject constructor(): DomainToUiMapper {
        override fun map(input: Flow<List<City>>) =
            input.map { list ->
                list.map {
                    it.let { (city, weather) ->
                        WeatherItem(city, weather)
                    }
                }
            }
    }
}