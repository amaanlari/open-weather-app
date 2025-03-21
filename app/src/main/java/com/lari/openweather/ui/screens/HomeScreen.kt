package com.lari.openweather.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview(showBackground = true)
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val weatherData by viewModel.weatherData.collectAsState()
    var city by remember {
        mutableStateOf("")
    }
    val apiKey = "ed522b4921624b271373e6c0db806a01"

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(180.dp))
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter City") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.fetchWeather(city, apiKey) }) {
                Text("Check Weather")
            }
            Spacer(modifier = Modifier.height(16.dp))

            weatherData?.let {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherCard(
                        label = "City",
                        value = "${it.name}°C",
                        icon = Icons.Default.Place
                    )
                    WeatherCard(
                        label = "Temperature",
                        value = "${it.main.temp}%",
                        icon = Icons.Default.Star
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherCard(
                        label = "Description",
                        value = it.weather[0].description,
                        icon = Icons.Default.Menu
                    )

                    WeatherCard(
                        label = "Humidity",
                        value = it.main.humidity.toString(),
                        icon = Icons.Default.Warning
                    )
                }
            }
        }
    }
}


@Composable
fun WeatherCard(label: String, value: String, icon: ImageVector) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = icon, contentDescription = null,
                    tint = Color.DarkGray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(label, fontSize = 14.sp, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    value,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            }
        }
    }

}