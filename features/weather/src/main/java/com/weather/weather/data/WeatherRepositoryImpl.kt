package com.weather.weather.data

import android.util.Log
import com.weather.weather.data.cache.CacheDataSource
import com.weather.weather.data.cloud.model.Temp
import com.weather.weather.data.cloud.WeatherApi
import com.weather.weather.data.mapper.DataToDomainMapper
import com.weather.weather.domain.model.WeatherItem
import com.weather.weather.domain.repository.WeatherRepository
import com.weatherapp.core.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cacheDataSource: CacheDataSource,
    private val mapper: DataToDomainMapper
): WeatherRepository {

    override val mutableStateFlow: MutableStateFlow<Result<WeatherItem>> = MutableStateFlow(Result.Loading())

    override suspend fun fetchWeather(city: String) {
        mutableStateFlow.value = Result.Loading()
        val call = weatherApi.fetchWeather(city)
        call.enqueue(object : Callback<Temp>{
            override fun onResponse(call: Call<Temp>, response: Response<Temp>) {
                val body = response.body()
                if (body != null){
                    if(response.isSuccessful) {
                        val main = body.main
                        if (main != null){
                            Log.e("TAG", "fetchWeather")
                            mutableStateFlow.value = Result.Success(mapper.map(Pair(city, main.temp)))
                        } else mutableStateFlow.value = Result.Error("main is null")
                    } else mutableStateFlow.value = Result.Error("Response isSuccessful == false")
                } else mutableStateFlow.value = Result.Error("Response is null")
            }

            override fun onFailure(call: Call<Temp>, t: Throwable) {
                mutableStateFlow.value = Result.Error("Failure: ${t.message}")
            }
        })
    }

    override suspend fun saveCity(city: Pair<String, String>) = cacheDataSource.saveCity(city)


}