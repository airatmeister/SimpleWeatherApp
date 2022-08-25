package com.weather.weather.presentation.mapper

import com.weather.weather.domain.model.WeatherItem
import org.junit.Assert
import org.junit.Test

class DomainToUiMapperTest {

    @Test
    fun test_map(){
        val mapper = DomainToUiMapper.Base()
        Assert.assertEquals(
            mapper.map(WeatherItem(city = "Moscow", weather = "30")),
            com.weather.weather.presentation.model.WeatherItem(city = "Moscow", weather = "30")
        )
    }
}