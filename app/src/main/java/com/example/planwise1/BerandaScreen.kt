package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
@Composable
fun Beranda(navHostController: NavHostController) {
    var plant by remember { mutableStateOf(TextFieldValue("")) }
    var selectedMenu by remember { mutableStateOf("home") }
    var isPressed by remember { mutableStateOf(false) }
    var isPressedpo by remember { mutableStateOf(false) }

    val menuItems = listOf(
        "home" to listOf(R.drawable.btnhomeoff, R.drawable.btnhome),
        "kamus" to listOf(R.drawable.btnkamus, R.drawable.btnkamuson),
        "kamera" to listOf(R.drawable.btnkamera, R.drawable.btnkameraon),
        "tanamanku" to listOf(R.drawable.btnmyplant, R.drawable.btnmyplanton),
        "komunitas" to listOf(R.drawable.btnkomunitas, R.drawable.btnkomunitason),
    )

    val artikel = listOf(
        R.drawable.btnartikel,
        R.drawable.btnartikelon
    )
    val populer = listOf(
        R.drawable.btnpopuler,
        R.drawable.btnpopuleroff
    )

    val popularPlants = listOf(
        R.drawable.group1,
        R.drawable.group2,
        R.drawable.group3
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
                            text = "Selamat Datang! Ghifari",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 9.dp)
                        )

                        Spacer(modifier = Modifier.height(50.dp))

                        IconButton(
                            onClick = {},
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
                            onClick = { /* TODO */ },
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
                            .padding(start = 20.dp, top = 20.dp) // Menambahkan padding untuk memberi jarak dari gambar
                            .align(Alignment.TopStart) // Menempatkan di posisi kiri atas
                    ) {
                        // Tanggal dan Lokasi
                        Text(
                            text = "Sabtu, 10 Oktober",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Purwokerto",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                        )

                        // Suhu
                        Text(
                            text = "30°C",
                            fontSize = 40.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )

                        // Status Cuaca
                        Text(
                            text = "Cerah",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .padding(top = 10.dp)
                        )
                    }
                }

                // Box untuk Gambar dan Informasi Angin
                Box(
                    modifier = Modifier
                        .padding(start = 185.dp, top = 1.dp)
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
                            text = "5 km/j",
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }
                }
                Box(modifier = Modifier
                    .padding(start = 312.dp)
                    .offset(y = -264.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.infoac),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 25.dp, top = 10.dp)
                            .offset(y = -3.dp)) {
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
                Image(
                    painter = painterResource(id = R.drawable.infocuaca),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 200.dp, )
                        .offset(y = -330.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.infocahaya),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 325.dp, top = 1.dp)
                        .offset(y = -360.dp)
                )
                Text(
                    text = "20%",
                    fontSize = 26.sp,
                    color = Color(0XFFFFD600),
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(top = 1.dp, start = 321.dp)
                        .offset(y = -340.dp)
                )
            }

            // Add the new content below the weather info
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .offset(y = -340.dp)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,) {
                        IconButton(onClick = { isPressed = !isPressed },
                            modifier = Modifier
                                .width(150.dp)
                                .height(68.dp)) {
                            Image(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(68.dp),
                                painter = painterResource(id = if (isPressed) artikel[1] else artikel[0]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        IconButton(onClick = { isPressedpo =!isPressedpo },
                            modifier = Modifier
                                .width(150.dp)
                                .height(58.dp)) {
                            Image(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(58.dp),
                                painter = painterResource(id = if (isPressedpo) populer[1] else populer[0]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }



        }

        // Bottom Navigation
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 1.dp, )
                .offset(y = 37.dp)
        ) {
            NavigationBar (
                containerColor = Color.White // Set the background color to white
            ){
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
                                "kamera" -> navHostController.navigate("kamera_screen")
                                "tanamanku" -> navHostController.navigate("tanamanku_screen")
                                "komunitas" -> navHostController.navigate("komunitas_screen")
                            }
                        }
                    )
                }
            }
        }
    }
}

