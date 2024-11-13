package com.example.planwise1


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@Composable
fun BerandaScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 54.dp)
    ) {
        // Top greeting and notification icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture), // Replace with actual image
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Selamat Datang! Ghifari",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = { /* Handle notifications */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search bar
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle search input */ },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            placeholder = { Text("Search your Plants") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weather information section
        WeatherCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Article and Popular Plants buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Handle article button click */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Artikel")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Handle popular plants button click */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Tanaman Populer", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Plants list
        PlantListItem("Kaktus", R.drawable.cactus_image) // Replace with actual image ID
        PlantListItem("Aglonema", R.drawable.aglonema_image) // Replace with actual image ID
        PlantListItem("Aglonema", R.drawable.aglonema_image) // Replace with actual image ID
        PlantListItem("Aglonema", R.drawable.aglonema_image) // Replace with actual image ID
        PlantListItem("Aglonema", R.drawable.aglonema_image) // Replace with actual image ID
        // Add more PlantListItem calls as needed
    }
}

class WeatherViewModel : ViewModel() {
    // State variables to hold the weather data
    private val _day = mutableStateOf("")
    val day: State<String> = _day

    private val _location = mutableStateOf("")
    val location: State<String> = _location

    private val _temperature = mutableStateOf("")
    val temperature: State<String> = _temperature

    private val _climate = mutableStateOf("")
    val climate: State<String> = _climate

    private val _humidity = mutableStateOf("")
    val humidity: State<String> = _humidity

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        // You would typically make an API request here. This is a placeholder.
        viewModelScope.launch {
            // Example response; replace with actual API request logic
            _day.value = "Monday, 6 November"
            _location.value = "Purwokerto"
            _temperature.value = "28°C"
            _climate.value = "Cloudy"
            _humidity.value = "65%"
        }
    }
}

@Composable

fun WeatherCard(viewModel: WeatherViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val day by viewModel.day
    val location by viewModel.location
    val temperature by viewModel.temperature
    val climate by viewModel.climate
    val humidity by viewModel.humidity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                Brush.horizontalGradient(listOf(Color(0xFF80DEEA), Color(0xFF4CAF50))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            // Date and Location
            Text(day, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(location, fontSize = 14.sp, color = Color.LightGray)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Temperature Section
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(temperature, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(climate, fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Weather Icon Section
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cloudy_icon), // Replace with appropriate weather icon
                        contentDescription = climate,
                        modifier = Modifier.size(40.dp),
                        tint = Color(0xFFFDD835)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Humidity Section
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sunny_icon), // Replace with your icon
                        contentDescription = "Humidity",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Yellow
                    )
                    Text("Humidity: $humidity", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}




@Composable
fun PlantListItem(name: String, imageResId: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)  // Adjust vertical padding to balance spacing
            .background(
                Brush.horizontalGradient(listOf(Color(0xFFB3E5FC), Color(0xFF4CAF50))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))  // Rounded corners for the image
                    .background(Color.White)  // Optional: add background to image for contrast
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White  // Optional: adjust color to match the gradient
            )
        }
    }
}

@Preview
@Composable
fun BerandaScreenPreview(){
    BerandaScreen()
}

