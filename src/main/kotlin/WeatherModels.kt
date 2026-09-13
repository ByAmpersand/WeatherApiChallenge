package com.weatherchallenge
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val forecast: Forecast
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("avghumidity") val avgHumidity: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double
)

data class Hour(
    val time: String,
    @SerializedName("wind_dir") val windDir: String
)