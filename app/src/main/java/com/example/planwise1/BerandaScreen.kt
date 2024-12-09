package com.example.planwise1

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.planwise1.data.SharedPrePreferencesManager

@Composable
fun Beranda(navHostController: NavHostController){
    var plant by remember { mutableStateOf(TextFieldValue("")) }
    var selectedMenu by remember { mutableStateOf("home") }
    var isPressed by remember { mutableStateOf(false) }
    var isPressedpo by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPrePreferencesManager(context)
    // Ambil data pengguna
    val username = sharedPreferencesManager.name ?: "Pengguna"

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

    /*Tombol navigasi*/
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.navigasi),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(70.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                menuItems.forEach { (menu, icons) ->
                    IconButton(
                        onClick = {
                            selectedMenu = menu
                            when (menu) {
                                "home" -> { /* Tidak perlu aksi tambahan */ }
                                "kamus" -> navHostController.navigate("kamus_screen")
                                "kamera" -> navHostController.navigate("camera_screen")
                                "tanamanku" -> navHostController.navigate("myplant_screen")
                                "komunitas" -> navHostController.navigate("komunitas_screen")
                            }
                        },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = if (selectedMenu == menu) icons[1] else icons[0]),
                            contentDescription = "$menu Icon"
                        )
                    }
                }
            }
        }
    }

    /*Kontent Beranda*/
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .height(845.dp)
        .background(color = Color(0XFFE9EFEC))) {
        item {
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 54.dp, start = 33.dp, end = 35.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {navHostController.navigate("profil_screen")},
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.profile1),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Selamat Datang! $username" /* nama diambil dari username sesuai saat login*/,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 30.dp)
                    )
                    IconButton(onClick = {},
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.btnnotif),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                /* pencarrian */
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
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = plant,
                                onValueChange = {newValue -> plant = newValue},
                                textStyle = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp
                                ),
                                decorationBox = { innerTextField ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
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

                    IconButton(onClick = {/*TODO*/},
                        modifier = Modifier
                            .size(48.dp))
                    {
                        Image(
                            modifier = Modifier
                                .size(48.dp),
                            painter = painterResource(id = R.drawable.btnkategori),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            /* informasi suhu*/
            Box(modifier = Modifier
                    .padding(start = 16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.infosuhu1),
                    contentDescription = null,
                    modifier = Modifier
                        .width(379.dp)
                        .height(191.dp),
                )

                /*keterangan suhu*/
                Column(modifier = Modifier
                    .padding(start = 20.dp, top = 15.dp)) {
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
                    Text(
                        text = "30°C",
                        fontSize = 65.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                    Text(
                        text = "Cerah",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
                /*keterangan kecepatan angin*/
                Box(modifier = Modifier
                    .padding(start = 180.dp, top = 120.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.infoangin),
                        contentDescription = null,
                        modifier = Modifier
                            .width(124.dp)
                            .height(68.dp)
                    )
                    Row(modifier = Modifier
                        .padding(start = 10.dp, top = 15.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.angin),
                            contentDescription = null,
                            modifier = Modifier
                                .width(35.dp)
                                .height(36.dp)
                        )
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
                /*keterangan suhu ruangan*/
                Box(modifier = Modifier
                    .padding(start = 300.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.infoac),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                        )
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 25.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ac),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            text = "24",
                            fontSize = 30.sp,
                            color = Color.Cyan,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.infocuaca),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 170.dp, top = 5.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.infocahaya),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 322.dp, top = 80.dp)
                )
                Text(
                    text = "20%",
                    fontSize = 30.sp,
                    color = Color(0XFFFFD600),
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(top = 140.dp, start = 322.dp)
                )
            }
        }
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(20.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,) {
                    IconButton(onClick = { isPressed = !isPressed },
                        modifier = Modifier
                            .width(150.dp)
                            .height(58.dp)) {
                        Image(
                            modifier = Modifier
                                .width(150.dp)
                                .height(58.dp),
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
}
