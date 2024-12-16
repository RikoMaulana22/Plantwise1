package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import com.example.planwise1.data.SharedPrePreferencesManager
import com.example.planwise1.viewmodel.WeatherResponse
import com.example.planwise1.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun Beranda(navHostController: NavHostController) {
    val viewModel:WeatherViewModel = viewModel()
    val weatherData by viewModel.weatherData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather("Purwokerto")
    }
    val currentDate = SimpleDateFormat("EEEE, dd MMMM", Locale("id")).format(Date())
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPrePreferencesManager(context)
    // Ambil data pengguna
    val username = sharedPreferencesManager.name ?: "Pengguna"
    var plant by remember { mutableStateOf(TextFieldValue("")) }
    var selectedMenu by remember { mutableStateOf("home") }
    var isArticleSelected by remember { mutableStateOf(true) }
    val menuItems = listOf(
        "home" to listOf(R.drawable.btnhomeoff, R.drawable.btnhome),
        "kamus" to listOf(R.drawable.btnkamus, R.drawable.btnkamuson),
        "kamera" to listOf(R.drawable.btnkamera, R.drawable.btnkameraon),
        "tanamanku" to listOf(R.drawable.btnmyplant, R.drawable.btnmyplanton),
        "komunitas" to listOf(R.drawable.btnkomunitas, R.drawable.btnkomunitason),
    )




    Box(modifier = Modifier.fillMaxSize()) {
        // Konten utama menggunakan LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0XFFE9EFEC))
                .padding(bottom = 50.dp) // Tambahkan padding untuk memberikan ruang bagi navigasi
        ) {
            item {
                // Header
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 54.dp, start = 33.dp, end = 35.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navHostController.navigate("profil_screen") },
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile1),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(
                            text = "Selamat Datang! $username",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 9.dp)
                        )

                        Spacer(modifier = Modifier.height(50.dp))

                        IconButton(
                            onClick = { navHostController.navigate("notif_screen")},
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.btnnotif),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    // Pencarian
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 280.dp, height = 48.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp)
                            ) {
                                BasicTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = plant,
                                    onValueChange = { newValue -> plant = newValue },
                                    textStyle = TextStyle(
                                        color = Color.Black,
                                        fontSize = 16.sp
                                    ),
                                    decorationBox = { innerTextField ->
                                        Box(modifier = Modifier.fillMaxWidth()) {
                                            if (plant.text.isEmpty()) {
                                                Text(
                                                    text = "Search your Plants",
                                                    color = Color.Gray,
                                                    fontSize = 16.sp
                                                )
                                            }
                                            innerTextField()
                                        }
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        IconButton(
                            onClick = { navHostController.navigate("kategori_screen") },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(48.dp),
                                painter = painterResource(id = R.drawable.btnkategori),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            // Konten Informasi Suhu
            item {
                Spacer(modifier = Modifier.height(20.dp))
                DisplayWeatherInfo(weatherData)
            }

            // Add the new content below the weather info
            item() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 1.dp)
                        .offset(y = -310.dp)
                ) {
                    // Toggle Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Artikel Button
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 5.dp)
                                .size(width = 130.dp, height = 50.dp) // Ukuran kotak yang lebih kecil
                                .background(
                                    brush = if (isArticleSelected) {
                                        Brush.linearGradient(
                                            colors = listOf(Color(0xFF7BE495), Color(0xFF329D9C)) // Warna gradien
                                        )
                                    } else {
                                        Brush.linearGradient(
                                            colors = listOf(Color(0xFFF5F5F5), Color(0xFFE0E0E0)) // Warna tombol tidak aktif
                                        )
                                    },
                                    shape = RoundedCornerShape(8.dp) // Bentuk kotak membulat
                                )
                                .clickable { isArticleSelected = true },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Artikel",
                                color = if (isArticleSelected) Color.White else Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Tanaman Populer Button
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp, end = 16.dp)
                                .size(width = 130.dp, height = 50.dp) // Ukuran kotak yang lebih kecil
                                .background(
                                    brush = if (!isArticleSelected) {
                                        Brush.linearGradient(
                                            colors = listOf(Color(0xFF7BE495), Color(0xFF329D9C)) // Warna gradien hijau
                                        )
                                    } else {
                                        Brush.linearGradient(
                                            colors = listOf(Color(0xFFF5F5F5), Color(0xFFE0E0E0)) // Warna gradien abu-abu
                                        )
                                    },
                                    shape = RoundedCornerShape(8.dp) // Bentuk kotak membulat
                                )
                                .clickable { isArticleSelected = false },

                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Tanaman Populer",
                                color = if (!isArticleSelected) Color.White else Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Content
                    if (isArticleSelected) {
                        // Artikel Content
                        DisplayPopularPlants()
                    } else {
                        // Tanaman Populer Content
                        DisplayArticles()
                    }
                }
            }


        }

        // Bottom Navigation
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 1.dp)
        ) {
            NavigationBar(
                containerColor = Color.White, // Latar belakang NavigationBar
            ) {
                menuItems.forEach { (menu, icons) ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = if (selectedMenu == menu) icons[1] else icons[0]),
                                contentDescription = menu
                            )
                        },
                        selected = selectedMenu == menu,
                        onClick = {
                            selectedMenu = menu
                            when (menu) {
                                "home" -> navHostController.navigate("beranda")
                                "kamus" -> navHostController.navigate("kamus_screen")
                                "kamera" -> navHostController.navigate("camera_screen")
                                "tanamanku" -> navHostController.navigate("myplant_screen")
                                "komunitas" -> navHostController.navigate("komunitas_screen")
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent, // Ikon saat dipilih
                            unselectedIconColor = Color.Transparent, // Ikon saat tidak dipilih
                            indicatorColor = Color.Transparent // Highlight/bayangan
                        )
                    )
                }
            }
        }


    }
}

@Composable
fun CurrentDateText() {
    val currentDate = remember {
        SimpleDateFormat("EEEE, dd MMMM", Locale("id")).format(Date())
    }

    Text(
        text = currentDate,
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DisplayWeatherInfo(weatherResponse: WeatherResponse?) {
    if (weatherResponse == null) {
        // Tampilkan placeholder atau pesan loading jika data belum tersedia
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading Cuaca...",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Memberikan jarak sama antara kanan dan kiri
        ) {
            // Gambar Background
            Image(
                painter = painterResource(id = R.drawable.infosuhu1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Agar gambar memenuhi lebar dengan jarak yang ditentukan
                    .aspectRatio(379f / 191f), // Menjaga proporsi gambar
            )

            // Konten Suhu
            Column(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 20.dp
                    ) // Menambahkan padding untuk memberi jarak dari gambar
                    .align(Alignment.TopStart) // Menempatkan di posisi kiri atas
            ) {
                CurrentDateText()

                Text(
                    text = weatherResponse.name ?: "Unknown Location",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                )

                // Suhu
                Text(
                    text = "${weatherResponse.main.temp}°C",
                    fontSize = 45.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                // Status Cuaca
                Text(
                    text = weatherResponse.weather.firstOrNull()?.description?.capitalize() ?: "Unknown Weather",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
            }
        }

        // Box untuk Gambar dan Informasi Angin
        Box(
            modifier = Modifier
                .padding(start = 195.dp, top = 1.dp)
                .offset(y = -70.dp) // Geser ke atas dengan offset negatif
        ) {
            // Gambar Angin
            Image(
                painter = painterResource(id = R.drawable.infoangin),
                contentDescription = null,
                modifier = Modifier
                    .width(124.dp)
                    .height(68.dp)
            )

            // Row untuk Angin dan Kecepatan Angin
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ikon Angin
                Image(
                    painter = painterResource(id = R.drawable.angin),
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(36.dp)

                )
                // Kecepatan Angin
                Text(
                    text = "${weatherResponse.wind.speed} km/j",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 320.dp)
                .offset(y = -264.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.infoac),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 25.dp, top = 10.dp)
                    .offset(y = -3.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ac),
                    contentDescription = null,
                    modifier = Modifier
                        .size(27.dp)

                )
                Text(
                    text = "24",
                    fontSize = 25.sp,
                    color = Color.Cyan,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }

        val temperature = weatherResponse.main.temp as? Double ?: 0.0

        val weatherIcon = when {
            temperature >= 30 -> R.drawable.cerah
            temperature in 20.0..29.9 -> R.drawable.berawan
            temperature in 10.0..19.9 -> R.drawable.awan
            else -> R.drawable.hujan
        }

        Image(
            painter = painterResource(id = weatherIcon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 200.dp,)
                .offset(y = -330.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.infocahaya),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 345.dp, top = 1.dp)
                .offset(y = -360.dp)
        )
        Text(
            text = "${weatherResponse.main.humidity}%",
            fontSize = 26.sp,
            color = Color(0XFFFFD600),
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(top = 1.dp, start = 345.dp)
                .offset(y = -340.dp)
        )
    }
}

@Composable
fun DisplayArticles() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ArticleItem(
            title = "Kaktus",
            backgroundColors = listOf(
                Color(0xFFFFF586).copy(alpha = 0.6f), // Warna oranye
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna merah-oranye
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Kaktus",
                    modifier = Modifier
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(140.dp)
                )
            }
        )
        ArticleItem(
            title = "Aglaonema",
            backgroundColors = listOf(
                Color(0xFF86FDFF).copy(alpha = 0.6f), // Warna hijau
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna hijau tua
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Aglaonema",
                    modifier = Modifier
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(140.dp)
                )
            }
        )
        ArticleItem(
            title = "Sirih Gading",
            backgroundColors = listOf(
                Color(0xFFFFF586).copy(alpha = 0.6f), // Warna biru
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna biru tua
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Sirih Gading",
                    modifier = Modifier
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(140.dp)
                )
            }
        )
        ArticleItem(
            title = "Aglaonema",
            backgroundColors = listOf(
                Color(0xFF86FDFF).copy(alpha = 0.6f), // Warna hijau
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna hijau tua
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Aglaonema",
                    modifier = Modifier
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(140.dp)
                )
            }
        )
    }
}

@Composable
fun ArticleItem(
    title: String,
    backgroundColors: List<Color>, // Tambahkan parameter warna
    image: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 35.dp)
            .height(120.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = backgroundColors // Warna dinamis
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(top = 1.dp, end = 5.dp, bottom = 7.dp)
            .offset(-15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar
            image()

            Spacer(modifier = Modifier.width(8.dp))

            // Teks
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}


@Composable
fun DisplayPopularPlants() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PlantItem(
            title = "Menahan Pandangan dari Anggrek yang Menggoda" ,
            backgroundColors = listOf(
                Color(0xFFFFF586).copy(alpha = 0.6f), // Warna oranye
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna merah-oranye
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Menahan Pandangan dari Anggrek yang Menggoda",
                    modifier = Modifier
                        .padding(start = 37.dp, end = 16.dp, top = 19.dp, bottom = 19.dp)
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(80.dp)
                )
            }
        )
        PlantItem(
            title = "Menahan Pandangan dari Anggrek yang Menggoda",
            backgroundColors = listOf(
                Color(0xFF86FDFF).copy(alpha = 0.6f), // Warna hijau
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna hijau tua
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Aglaonema",
                    modifier = Modifier
                        .padding(start = 37.dp, end = 16.dp, top = 19.dp, bottom = 19.dp)
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(80.dp)
                )
            }
        )
        PlantItem(
            title = "Menahan Pandangan dari Anggrek yang Menggoda",
            backgroundColors = listOf(
                Color(0xFFFFF586).copy(alpha = 0.6f), // Warna biru
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna biru tua
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Sirih Gading",
                    modifier = Modifier
                        .padding(start = 37.dp, end = 16.dp, top = 19.dp, bottom = 19.dp)
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(80.dp)
                )
            }
        )
        PlantItem(
            title = "Menahan Pandangan dari Anggrek yang Menggoda",
            backgroundColors = listOf(
                Color(0xFF86FDFF).copy(alpha = 0.6f), // Warna hijau
                Color(0xFF568A77).copy(alpha = 0.7f)  // Warna hijau tua
            ),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.aglonema_image),
                    contentDescription = "Aglaonema",
                    modifier = Modifier
                        .padding(start = 37.dp, end = 16.dp, top = 19.dp, bottom = 19.dp)
                        .offset(x = (-2).dp, y = (-25).dp)
                        .graphicsLayer(scaleX = 1.5f, scaleY = 1.5f)
                        .size(80.dp)
                )
            }
        )
    }
}

@Composable
fun PlantItem(
    title: String,
    backgroundColors: List<Color>, // Tambahkan parameter warna
    image: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 35.dp)

            .height(140.dp) // Tinggi bisa disesuaikan jika perlu
            .background(
                brush = Brush.horizontalGradient(
                    colors = backgroundColors // Warna dinamis
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(1.dp)
            .offset(-128.dp)// Padding internal untuk konten di dalam Box
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gambar
            image()

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding()
                    .offset(x = (120).dp, y = (-40).dp)            )
        }
    }
}