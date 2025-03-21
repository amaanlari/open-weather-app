package com.lari.openweather.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lari.openweather.api.WeatherApi
import com.lari.openweather.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData
    private val _weatherApi = WeatherApi.create()

    fun fetchWeather(city: String, apiKey: String, units: String="metric") {
        viewModelScope.launch {
            try {
                val response = _weatherApi.getWeather(city, apiKey, units)
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}