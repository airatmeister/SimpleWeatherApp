package com.weather.favorite.domain.interactor

import com.weather.favorite.domain.model.City
import com.weather.favorite.domain.repository.SavedCitiesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CitiesInteractorTest {

    @Test
    fun test_call_SavedCitiesRepository_deleteCity() = runBlocking {
        val savedCitiesRepository = Mock()
        CitiesInteractor.Base(savedCitiesRepository).deleteCity("Moscow")
        Assert.assertEquals(savedCitiesRepository.isCalled, true)
    }

    @Test
    fun test_call_SavedCitiesRepository_getCities() = runBlocking {
        val savedCitiesRepository = Mock()
        CitiesInteractor.Base(savedCitiesRepository).getSavedCities()
        Assert.assertEquals(savedCitiesRepository.isCalled, true)
    }

    @Test
    fun test_getCities_result() = runBlocking {
        val savedCitiesRepository = MockGetCities()
        CitiesInteractor.Base(savedCitiesRepository).getSavedCities().collect {
            Assert.assertEquals("${it[0].city} ${it[0].weather}", "Moscow 30")
        }
    }


    class Mock: SavedCitiesRepository {

        var isCalled = false

        override fun getCities(): Flow<List<City>> {
            isCalled = true
            return flow { emit(mutableListOf()) }
        }

        override fun deleteCity(city: String) {
            isCalled = true
        }
    }

    class MockGetCities: SavedCitiesRepository {
        override fun getCities() =
            flow { emit(mutableListOf(City(city = "Moscow", weather = "30"))) }

        override fun deleteCity(city: String) = Unit
    }
}