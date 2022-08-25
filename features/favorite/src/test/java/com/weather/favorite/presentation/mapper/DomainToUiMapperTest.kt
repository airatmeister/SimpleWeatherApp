package com.weather.favorite.presentation.mapper

import com.weather.favorite.domain.model.City
import com.weather.favorite.presentation.model.WeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class DomainToUiMapperTest {

    @Test
    fun test_map() = runBlocking{
        val mapper = DomainToUiMapper.Base()
        val flowExpected: Flow<List<City>> = flow { mutableListOf(City(city = "Moscow", weather = "30")) }
        mapper.map(flowExpected).collect {
            Assert.assertEquals(it, mutableListOf(WeatherItem(city = "Moscow", weather = "30")))
        }
    }
}