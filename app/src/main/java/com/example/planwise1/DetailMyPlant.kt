package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun DetailMyPlant(navHostController: NavHostController, dataStoreManager: DataStoreManager){
    val customShape = RoundedCornerShape(
        topStart = 30.dp,
        topEnd = 30.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
    val shapenav = RoundedCornerShape(
        topStart = 50.dp,
        topEnd = 50.dp,
        bottomStart = 50.dp,
        bottomEnd = 50.dp
    )
    val shapejadwal = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )
    val gradientColors = listOf(
        Color(0xFFCFF4D2),
        Color(0xFF56C596)
    )
    val saveTextFields = dataStoreManager.textFieldsFlow.collectAsState(initial = listOf("", "", "", ""))
    val textFieldValue = remember { mutableStateOf(saveTextFields.value[0]) }
    val textFieldValue2 = remember { mutableStateOf(saveTextFields.value[1]) }
    val textFieldValue3 = remember { mutableStateOf(saveTextFields.value[2]) }
    val textFieldValue4 = remember { mutableStateOf(saveTextFields.value[3]) }
    val expanded2 = remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()


    //konten
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0XFFE9EFEC))
        .padding(top = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,) {
        item {
            Image(
                painter = painterResource(id = R.drawable.kaktusdetail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(245.dp)
            )
        }
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(900.dp) // Ketinggian shape
                .background(
                    brush = Brush.linearGradient(
                        colors = gradientColors,
                        start = Offset(0f, 0f),
                        end = Offset(0f, 1000f)
                    ), // Warna latar
                    shape = customShape
                )
                .padding(16.dp) // Padding untuk konten di dalam shape

            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Kaktus Cereus",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier
                        .width(280.dp)
                        .height(44.dp)
                        .background(Color(0xFF6A9C89),
                            shape = shapenav)) {
                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.btnrawat),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(82.dp)
                                    .height(31.dp)
                                    .clickable {  }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.btncatatan),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(82.dp)
                                    .height(31.dp)
                                    .clickable { navHostController.navigate("detailplant2_screen") }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.btnbio),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(82.dp)
                                    .height(31.dp)
                                    .clickable { navHostController.navigate("detailplant3_screen") }
                            )
                        }
                    }

                    //Box Status
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth()) {
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly) {

                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                DropdownMenu(
                                    expanded = expanded2.value,
                                    onDismissRequest = { expanded2.value = false },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .clip(RoundedCornerShape(10.dp))

                                ) {
                                    // Konten DropdownMenu
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(308.dp)
                                            .padding(20.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        // TextField di dalam DropdownMenu
                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icbunga),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                            )
                                            TextField(
                                                value = textFieldValue.value,
                                                onValueChange = { newValue ->
                                                    textFieldValue.value = newValue
                                                },
                                                placeholder = { Text("Type something...") },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(Color.White)
                                            )
                                        }
                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icair2),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                            )
                                            TextField(
                                                value = textFieldValue2.value,
                                                onValueChange = { newValue ->
                                                    textFieldValue2.value = newValue
                                                },
                                                placeholder = { Text("Type something...") },
                                                modifier = Modifier
                                                    .width(250.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(Color.White)
                                            )
                                            Text(
                                                text = "ml",
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icpupuk2),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                            )
                                            TextField(
                                                value = textFieldValue3.value,
                                                onValueChange = { newValue ->
                                                    textFieldValue3.value = newValue
                                                },
                                                placeholder = { Text("Type something...") },
                                                modifier = Modifier
                                                    .width(250.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(Color.White)
                                            )
                                            Text(
                                                text = "gm",
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                        Row(modifier = Modifier
                                            .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween) {
                                            Image(
                                                painter = painterResource(id = R.drawable.icmatahari2),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                            )
                                            TextField(
                                                value = textFieldValue4.value,
                                                onValueChange = { newValue ->
                                                    textFieldValue4.value = newValue
                                                },
                                                placeholder = { Text("Type something...") },
                                                modifier = Modifier
                                                    .width(250.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(Color.White)
                                            )
                                            Text(
                                                text = "%",
                                                fontSize = 40.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Tombol untuk menyimpan data
                                        Button(
                                            onClick = {
                                                expanded2.value = false
                                                scope.launch {
                                                    dataStoreManager.saveTextFields(
                                                        textFieldValue.value,
                                                        textFieldValue2.value,
                                                        textFieldValue3.value,
                                                        textFieldValue4.value
                                                    )
                                                }
                                            },
                                            modifier = Modifier
                                                .width(275.dp)
                                                .height(35.dp)
                                        ) {
                                            Text("Edit")
                                        }
                                    }
                                }
                            }

                            Box(contentAlignment = Alignment.Center,
                                ) {
                                Image(
                                    painter = painterResource(id = R.drawable.shapestatus),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(173.dp)
                                        .height(86.dp)
                                        .clickable { expanded2.value = !expanded2.value }
                                )
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center) {
                                    Image(
                                        painter = painterResource(id = R.drawable.icair),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .width(20.dp)
                                            .height(29.dp)
                                    )
                                    Column(modifier = Modifier
                                        .padding(start = 10.dp)) {
                                        Text(
                                            text = "Air",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = "20ml",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                            Box(contentAlignment = Alignment.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.shapestatus),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(173.dp)
                                        .height(86.dp)
                                        .clickable {  }
                                )
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly) {
                                    Column(verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = "Pupuk",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icpupuk),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .width(25.dp)
                                                .height(24.dp)
                                        )
                                        Text(
                                            text = "5gm",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.Black
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(20.dp))
                                    Column(verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = "Cahaya",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.icmatahari),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .width(25.dp)
                                                .height(24.dp)
                                        )
                                        Text(
                                            text = "35-40%",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }

                    //Garis Pembatas
                    Spacer(modifier = Modifier.height(15.dp))
                    Image(
                        painter = painterResource(id = R.drawable.icgaris),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(357.dp)
                    )

                    //Slider Tanggal
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "Mei 2022",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "Lihat Kalender",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.icslidekalender),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 10.dp)
                        )
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navHostController.navigate("jadwal_screen") },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "18",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Sen",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "19",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Sel",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "20",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Rab",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "21",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Kam",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "22",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "jum",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "24",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Sab",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                            Column(verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "25",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )
                                Text(
                                    text = "Min",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Red
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Jadwal Hari Ini",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            // DropdownMenu
                            DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false }, // Tutup saat pengguna menekan luar menu
                                modifier = Modifier
                                    .width(100.dp) // Lebar menu lebih besar untuk tampilan lebih jelas
                                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 0.dp, bottomEnd = 30.dp, bottomStart = 30.dp)), // Radius khusus
                                offset = DpOffset(x = -10.dp, y = -5.dp) // Offset agar menu tepat di bawah tombol
                            ) {
                                // Menu items untuk Senin hingga Minggu
                                val days = listOf(
                                    "Senin",
                                    "Selasa",
                                    "Rabu",
                                    "Kamis",
                                    "Jumat",
                                    "Sabtu",
                                    "Minggu"
                                )
                                days.forEach { day ->
                                    DropdownMenuItem(
                                        text = { Text(day, fontSize = 16.sp) },
                                        onClick = {
                                            expanded.value = false
                                            // Aksi untuk hari tertentu (jika ada)
                                        }
                                    )
                                }
                            }
                            Box(contentAlignment = Alignment.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.btnhari),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(35.dp)
                                        .clickable {
                                            expanded.value = true // Buka dropdown menu
                                        }
                                )
                                Column(verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(bottom = 5.dp, end = 10.dp)) {
                                    Text(
                                        text = "Senin",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }

                    //Jadwal Pengingat
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "07.00 WIB", //NANTI AMBIL DARI DATABASE
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.Black
                        )
                        //tampilkan disini
                        Box(modifier = Modifier
                            .width(242.dp)
                            .height(47.dp)
                            .background(Color(0xFF16423C),
                                shape = shapejadwal)) {
                        }
                    }
                }
            }
        }
    }

    //header
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Box {
            Box(
                modifier = Modifier
                    .size(width = 412.dp, height = 110.dp)
                    .background(color = Color.White)
            )
            Box(modifier = Modifier
                .fillMaxWidth())
            {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 30.dp, top = 50.dp)) {
                    IconButton(onClick = { navHostController.navigate("myplant_screen") },
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.btnback),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "MyPlant",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 20.dp)
                    )
                }
            }
        }
    }
}