package com.weather.favorite.data.mapper

import com.weather.favorite.domain.model.City
import com.weatherapp.core.common.Mapper
import com.weatherapp.core.data.db.CityEntity
import javax.inject.Inject

interface DataToDomainMapper: Mapper<CityEntity, City> {

    class Base @Inject constructor(): DataToDomainMapper {
        override fun map(input: CityEntity) =
            input.let { (_, city, weather) ->
                City(city = city, weather = weather)
            }
    }
}