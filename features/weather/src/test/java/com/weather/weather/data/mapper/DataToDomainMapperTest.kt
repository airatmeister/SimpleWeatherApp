package com.weather.weather.data.mapper

import com.weather.weather.domain.model.WeatherItem
import org.junit.Assert
import org.junit.Test

class DataToDomainMapperTest {

    @Test
    fun test_map(){
        val mapper = DataToDomainMapper.Base()
        Assert.assertEquals(mapper.map(Pair("Moscow", 30f)), WeatherItem("Moscow", "30.0"))
    }
}