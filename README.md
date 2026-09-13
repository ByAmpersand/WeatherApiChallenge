# Weather API Code Challenge

This is a console application written in Kotlin that retrieves the next day's forecast for Chisinau, Madrid, Kyiv, and Amsterdam using the WeatherAPI.com API.

## Requirements Met
- **Language:** Kotlin
- **Build System:** Gradle
- **HTTP Client:** Retrofit + Gson for JSON parsing
- **Output:** STDOUT formatted as a table

*Note on Wind Direction:* The API's `day` object does not provide wind direction for the daily forecast. To fulfill this requirement, the application extracts the wind direction from the `hour` array specifically for 12:00 PM of the next day.

## How to Run

1. Clone the repository.
2. Create a file named `config.properties` in the root directory of the project.
3. Add your WeatherAPI key to the file in the following format:
   ```properties
   API_KEY=your_api_key_here

Run the application using Gradle:

`./gradlew run`

Or run the Main.kt file directly from your IDE.