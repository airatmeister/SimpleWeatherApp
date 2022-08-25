package com.weather.weather.data

import com.weather.weather.data.cache.CacheDataSource
import com.weather.weather.data.cloud.WeatherApi
import com.weather.weather.data.mapper.DataToDomainMapper
import com.weather.weather.domain.model.WeatherItem
import com.weatherapp.core.common.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepositoryImplTest {

    private val weatherApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .build().create(WeatherApi::class.java)

    @Test
    fun test_call_CacheDataSource_saveCity() = runBlocking{
        val cacheDataSource = MockCacheDataSource()
        val mapper = MockDataToDomainMapper()
        WeatherRepositoryImpl(weatherApi, cacheDataSource, mapper).saveCity(Pair("Moscow", "30"))
        Assert.assertEquals(cacheDataSource.isCalled, true)
    }

    @Test
    fun test_check_first_stateflow_value() = runBlocking{
        val cacheDataSource = MockCacheDataSource()
        val mapper = MockDataToDomainMapper()
        val weatherRepositoryImpl = WeatherRepositoryImpl(weatherApi, cacheDataSource, mapper)
        weatherRepositoryImpl.saveCity(Pair("Moscow", "30"))
        Assert.assertEquals(weatherRepositoryImpl.mutableStateFlow.value.javaClass, Result.Loading<WeatherItem>().javaClass)
    }

    class MockDataToDomainMapper: DataToDomainMapper {
        var isCalled = false

        override fun map(input: Pair<String, Float>): WeatherItem {
            isCalled = true
            return WeatherItem("Moscow", "30")
        }
    }

    class MockCacheDataSource: CacheDataSource {

        var isCalled = false

        override suspend fun saveCity(city: Pair<String, String>) {
            isCalled = true
        }
    }
}