package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MyPlantScreen(navHostController: NavHostController){
    val menuItems = listOf(
        Pair(R.drawable.tanaman1, "Kaktus"),
        Pair(R.drawable.tanaman2, "Kaktus2"),
        Pair(R.drawable.tanaman3, "Kaktus3"),
        Pair(R.drawable.tanaman1, "Kaktus4"),
        Pair(R.drawable.tanaman2, "Kaktus5"),
        Pair(R.drawable.tanaman3, "Kaktus6"),
        Pair(R.drawable.tanaman1, "Kaktus7"),
        Pair(R.drawable.tanaman2, "Kaktus8"),
        Pair(R.drawable.tanaman3, "Kaktus9"),
        Pair(R.drawable.tanaman1, "Kaktus10"),
        Pair(R.drawable.tanaman2, "Kaktus11"),
        Pair(R.drawable.tanaman3, "Kaktus12"),
    )

    val gradientColors = listOf(
        Color(0xFFCFF4D2), // Warna pertama
        Color(0xFF56C596)  // Warna kedua
    )

    //header
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White),
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
                    IconButton(onClick = { navHostController.navigate("beranda_screen") },
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

        //FOLDER TANAMANKU
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = "MyRoom",
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = { /*TODO:*/ },
                        modifier = Modifier
                            .width(170.dp)
                            .height(115.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.btnindoor),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(170.dp)
                                .height(115.dp)
                        )
                    }
                    Text(
                        text = "Indoor",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = { /*TODO:*/ },
                        modifier = Modifier
                            .width(170.dp)
                            .height(115.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.btnoutdoor),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(170.dp)
                                .height(115.dp)
                        )
                    }
                    Text(
                        text = "Outdoor",
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "TanamanKu",
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
            )
        }

        //KONTENNYA
        Box {
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                            start = Offset(0f, 0f),
                            end = Offset(0f, 1000f)
                        )
                    )
            ) {
                items(menuItems) { (imageRes, description) ->
                    MenuItem(
                        imageRes = imageRes,
                        description = description,
                        onClick = { navHostController.navigate("detailplant_screen")}
                    )
                }
            }

            FloatingActionButton(
                onClick = { /* TODO: Handle FAB click */ },
                contentColor = Color.White,
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.btnfloating),
                    contentDescription = "Add Plant"
                )
            }
        }
    }
}


@Composable
fun MenuItem(
    imageRes: Int,
    description: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(170.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(112.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
