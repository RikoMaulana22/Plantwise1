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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DetailMyPlant3(navHostController: NavHostController){
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
    val boxinterior = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 20.dp
    )
    val gradientColors = listOf(
        Color(0xFFCFF4D2),
        Color(0xFF56C596)
    )

    //konten
    LazyColumn(modifier = Modifier
        .fillMaxSize()
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                            start = Offset(0f, 0f),
                            end = Offset(0f, 1000f)
                        ), // Warna latar
                        shape = customShape
                    )

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Kaktus Cereus",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .width(280.dp)
                            .height(44.dp)
                            .background(
                                Color(0xFF6A9C89),
                                shape = shapenav
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.btnrawatoff),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(82.dp)
                                    .height(31.dp)
                                    .clickable { navHostController.navigate("detailplant_screen") }
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
                                painter = painterResource(id = R.drawable.btnbioon),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(82.dp)
                                    .height(31.dp)
                                    .clickable { }
                            )
                        }
                    }

                    Text( //TEKS NANTI DIAMBIL DARI API
                        text = "Kaktus Cereus adalah genus kaktus berbentuk kolom yang tumbuh tinggi dan tegak, " +
                                "sering ditemukan di Amerika Selatan dan wilayah gurun Amerika Utara. " +
                                "Kaktus ini memiliki batang berduri dengan warna hijau hingga biru kehijauan dan dapat tumbuh hingga beberapa meter. " +
                                "Cereus menghasilkan bunga besar berwarna putih yang mekar di malam hari " +
                                "dan menarik penyerbuk seperti ngengat dan kelelawar.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(20.dp)
                    )
                }
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(300.dp))
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.shapestatus2),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, top = 10.dp, bottom = 10.dp, end = 30.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start) {
                            Text(
                                text = "Status",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Row(modifier = Modifier
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {

                                //status air
                                Column(verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icair),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                        )
                                        Column(verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start,
                                            modifier = Modifier
                                                .padding(start = 10.dp)) {
                                            Text(
                                                text = "250ml",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                            Text(
                                                text = "AIR",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                    }
                                }

                                //status matahari
                                Column(verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icmatahari),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                        )
                                        Column(verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start,
                                            modifier = Modifier
                                                .padding(start = 10.dp)) {
                                            Text(
                                                text = "35%-40%",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                            Text(
                                                text = "CAHAYA",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                    }
                                }

                                //status pupuk
                                Column(verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icpupuk),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                        )
                                        Column(verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start,
                                            modifier = Modifier
                                                .padding(start = 10.dp)) {
                                            Text(
                                                text = "250gm",
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                            Text(
                                                text = "PUPUK",
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //Informasi Tanaman
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.btnhistory),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(353.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.btnrekomendasi),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(353.dp)
                                .padding(top = 10.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.btncaraperawatan),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(353.dp)
                                .padding(top = 10.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.papaniklan),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(332.dp)
                                .padding(top = 36.dp)
                        )

                        //Konten LazyRow
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start) {
                            Text(
                                text = "Interior Ruangan",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            LazyRow(modifier = Modifier
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start) {
                                item {
                                    Box(modifier = Modifier
                                        .width(142.dp)
                                        .height(166.dp)
                                        .background(Color.White, shape = boxinterior),
                                        contentAlignment = Alignment.Center) {
                                        Image(
                                            painter = painterResource(id = R.drawable.interior1),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .width(120.dp)
                                                .height(137.dp)
                                        )
                                    }
                                }
                                item {
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Box(modifier = Modifier
                                        .width(142.dp)
                                        .height(166.dp)
                                        .background(Color.White, shape = boxinterior),
                                        contentAlignment = Alignment.Center) {
                                        Image(
                                            painter = painterResource(id = R.drawable.interior1),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .width(120.dp)
                                                .height(137.dp)
                                        )
                                    }
                                }
                                item {
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Box(modifier = Modifier
                                        .width(142.dp)
                                        .height(166.dp)
                                        .background(Color.White, shape = boxinterior),
                                        contentAlignment = Alignment.Center) {
                                        Image(
                                            painter = painterResource(id = R.drawable.interior1),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .width(120.dp)
                                                .height(137.dp)
                                        )
                                    }
                                }
                            }
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