package com.weather.weather.domain.interactors

import com.weather.weather.domain.model.WeatherItem
import com.weather.weather.domain.repository.WeatherRepository
import com.weatherapp.core.common.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import org.junit.Assert
import org.junit.Test

class WeatherInteractorTest {

    @Test
    fun test_call_WeatherRepository_fetchWeather() = runBlocking{
        val repository = MockWeatherRepository()
        val interactor = WeatherInteractor.Base(repository)
        interactor.executeSearchCity("Moscow").first()
        Assert.assertEquals(repository.isCalled, true)
    }

    @Test
    fun test_first_result_executeSearchCity() = runBlocking{
        val repository = MockWeatherRepository()
        val interactor = WeatherInteractor.Base(repository)
        val result = interactor.executeSearchCity("Moscow").first()
        Assert.assertEquals(result.javaClass, Result.Loading<WeatherItem>().javaClass)
    }

    @Test
    fun test_call_WeatherRepository_saveCity() = runBlocking{
        val repository = MockWeatherRepository()
        val interactor = WeatherInteractor.Base(repository)
        interactor.saveCity("Moscow", "30")
        Assert.assertEquals(repository.isCalled, true)
    }

    class MockWeatherRepository: WeatherRepository {

        var isCalled = false

        override val mutableStateFlow: MutableStateFlow<Result<WeatherItem>> = MutableStateFlow(Result.Loading())

        override suspend fun fetchWeather(city: String) {
            isCalled = true
            mutableStateFlow.value = Result.Success(WeatherItem("Moscow", "30"))
        }

        override suspend fun saveCity(city: Pair<String, String>) {
            isCalled = true
        }
    }
}