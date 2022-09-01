package com.example.simpledaggerhiltapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledaggerhiltapp.utils.Resource
import com.example.simpledaggerhiltapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    val repository: WeatherRepository
): ViewModel() {
    sealed class WeatherEvent{
        class Success(val resultText:String):WeatherEvent()
        class Failure(val errorText:String):WeatherEvent()
        object Loading:WeatherEvent()
        object Empty:WeatherEvent()
    }
    private val _conversion = MutableStateFlow<WeatherEvent>(WeatherEvent.Empty)
    val conversion: StateFlow<WeatherEvent> = _conversion

    fun getCurrentWeather(){
        viewModelScope.launch {
            _conversion.value=WeatherEvent.Loading
            when(val weatherResponse=repository.getWeatherCity()){
                is Resource.Success->{
                    val data = weatherResponse.data?.main
                    if(data==null){
                        _conversion.value =WeatherEvent.Failure(weatherResponse.message!!)
                    }else{
                        _conversion.value=WeatherEvent.Success("$data")
                    }
                }
            }
        }
    }


}