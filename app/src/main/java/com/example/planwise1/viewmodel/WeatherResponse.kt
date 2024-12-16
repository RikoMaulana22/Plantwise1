package com.example.planwise1.viewmodel

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val name: String
)

data class Coord(val lon: Double, val lat: Double)
data class Weather(val main: String, val description: String, val icon: String)
data class Main(val temp: Double, val pressure: Int, val humidity: Int)
data class Wind(val speed: Double, val deg: Int)