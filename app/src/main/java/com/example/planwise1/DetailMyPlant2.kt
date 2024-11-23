package com.example.planwise1

import android.content.Context
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DetailMyPlant2(navHostController: NavHostController, dataStoreManager: DataStoreManager) {
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
    val gradientColors = listOf(
        Color(0xFFCFF4D2),
        Color(0xFF56C596)
    )
    val save = listOf(
        R.drawable.btnsave,
        R.drawable.btnsaveon
    )
    var isPressed by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)

    // Membaca data dari DataStore
    val savedNote by dataStoreManager.noteFlow.collectAsState(initial = "")

    LaunchedEffect(savedNote) {
        textFieldValue = savedNote
    }


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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(575.dp) // Ketinggian shape
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
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
                                painter = painterResource(id = R.drawable.btncatatanon),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(82.dp)
                                    .height(31.dp)
                                    .clickable {  }
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

                    Spacer(modifier = Modifier.height(16.dp))

                    Box {
                        BasicTextField(
                            value = textFieldValue,
                            onValueChange = { newValue -> textFieldValue = newValue },
                            modifier = Modifier
                                .size(width = 385.dp, height = 433.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(16.dp),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            ),
                            decorationBox = { innerTextField ->
                                if (textFieldValue.isEmpty()) {
                                    Text(
                                        text = "Tambahkan Catatan",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
                            }
                        )

                        // Tombol Save
                        Image(
                            painter = painterResource(id = if (isPressed) save[1] else save[0]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                                .clickable {
                                    isPressed = !isPressed
                                    // Simpan catatan ke DataStore
                                    if (isPressed) {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            dataStoreManager.saveNote(textFieldValue)
                                        }
                                    }
                                }
                        )
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