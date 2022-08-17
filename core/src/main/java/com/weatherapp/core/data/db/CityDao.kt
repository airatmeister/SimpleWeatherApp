package com.weatherapp.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(city: CityEntity)

    @Query("SELECT * FROM saved_cities ORDER by id")
    fun getSavedCities(): Flow<List<CityEntity>>

    @Query("DELETE FROM saved_cities WHERE city = :city")
    fun deleteByCity(city: String)
}