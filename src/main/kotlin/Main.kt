package com.weatherchallenge

import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileInputStream
import java.util.Properties
import java.util.Locale

fun main() = runBlocking {

    val properties = Properties()
    try {
        properties.load(FileInputStream("config.properties"))
    } catch (e: Exception) {
        println("Error: config.properties file not found. Please create it and add API_KEY=your_key")
        return@runBlocking
    }

    val apiKey = properties.getProperty("API_KEY")
    if (apiKey.isNullOrEmpty()) {
        println("Error: API_KEY is missing in config.properties")
        return@runBlocking
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(WeatherApiService::class.java)

    val cities = listOf("Chisinau", "Madrid", "Kyiv", "Amsterdam")

    println(String.format("%-12s | %-12s | %-10s | %-10s | %-10s | %-12s | %-15s",
        "City", "Date", "Min Temp", "Max Temp", "Humidity", "Wind Speed", "Wind Dir (12:00)"))
    println("-".repeat(95))

    for (city in cities) {
        try {

            val response = api.getForecast(apiKey, city, days = 2)

            val tomorrow = response.forecast.forecastday[1]
            val date = tomorrow.date
            val dayData = tomorrow.day

            val windDir = tomorrow.hour[12].windDir

            println(String.format(Locale.US, "%-12s | %-12s | %-8.1f C | %-8.1f C | %-9.0f%% | %-8.1f kph | %-15s",                city,
                date,
                dayData.minTempC,
                dayData.maxTempC,
                dayData.avgHumidity,
                dayData.maxWindKph,
                windDir
            ))

        } catch (e: Exception) {
            println(String.format("%-12s | %-80s", city, "Error fetching data: ${e.message}"))
        }
    }
}