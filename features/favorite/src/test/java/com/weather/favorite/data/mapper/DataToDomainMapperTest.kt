package com.weather.favorite.data.mapper

import com.weather.favorite.domain.model.City
import com.weatherapp.core.data.db.CityEntity
import org.junit.Assert
import org.junit.Test

class DataToDomainMapperTest {

    @Test
    fun test_map(){
        val cityEntity = CityEntity(id = 0, city = "Moscow", weather = "30")
        val mapper = DataToDomainMapper.Base()
        Assert.assertEquals(mapper.map(cityEntity), City(city = "Moscow", weather = "30"))
    }
}