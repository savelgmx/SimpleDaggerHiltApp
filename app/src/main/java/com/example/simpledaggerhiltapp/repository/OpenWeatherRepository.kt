package com.example.simpledaggerhiltapp.repository


import com.example.simpledaggerhiltapp.utils.Resource
import com.example.simpledaggerhiltapp.response.WeatherCityResponse
import com.zaidzakir.mediumexample.model.api.OpenWeatherApi


import java.lang.Exception
import javax.inject.Inject


class OpenWeatherRepository @Inject constructor(
    val openWeatherApi: OpenWeatherApi
    ) : WeatherRepository {
    override suspend fun getWeatherCity(): Resource<WeatherCityResponse> {
        return try {
            val response =openWeatherApi.getWeatherOfCity("Krasnoyarsk","metric")
            val result =response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error("unknown error")
            }
        }catch (e:Exception){
            println("opeWeatherApi $e")
            Resource.Error("An $e occured")
        }


    }

}


