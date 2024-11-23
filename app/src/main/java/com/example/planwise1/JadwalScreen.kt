package com.example.planwise1

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JadwalScreen(navHostController: NavHostController) {
    var dateResult by remember { mutableStateOf("Date Picker") }
    val openDialog = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isPressed by remember { mutableStateOf(false) }
    val pengingat = remember { mutableStateListOf<String>() }
    var showCalendar by remember { mutableStateOf(false) }

    val gradientColors = listOf(
        Color(0xFFCFF4D2), // Warna pertama
        Color(0xFF56C596)  // Warna kedua
    )
    val kirim = listOf(
        R.drawable.btntambah,
        R.drawable.btntambahon,
    )
    Box(modifier = Modifier
        .fillMaxWidth())
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = gradientColors,
                        start = Offset(0f, 0f),
                        end = Offset(0f, 1000f)
                    )
                )
                .padding(bottom = 80.dp, top = 669.dp),
            reverseLayout = false,
        )
        {
            items(pengingat) { pengingat ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier
                            .width(353.dp) // Lebar tetap 353.dp
                            .background(Color(0xFFFC4DAD2), RoundedCornerShape(20.dp))
                            .clickable {
                                openDialog.value = true // Menampilkan DatePicker saat pengingat diklik
                            }
                    ) {
                        Text(
                            text = pengingat,
                            fontSize = 30.sp, // Ukuran font 30.sp
                            color = Color.Black,
                            modifier = Modifier
                                .padding(15.dp) // Padding di dalam box
                                .align(Alignment.Center) // Agar teks berada di tengah
                        )
                    }
                }
            }
        }

        // Menampilkan kalender hanya jika showCalendar bernilai true
        if (showCalendar) {
            Spacer(modifier = Modifier.height(20.dp))
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.kalender),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding( bottom = 20.dp))
            {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(339.dp)
                        .align(Alignment.Center))
                {
                    BasicTextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        modifier = Modifier
                            .size(width = 280.dp, height = 45.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        if (text.text.isEmpty()) {
                                            Text(
                                                textAlign = TextAlign.Center,
                                                text = "Tambah Pengingat",
                                                style = TextStyle(color = Color.Gray, fontSize = 16.sp),
                                            )
                                        }
                                        innerTextField()
                                    }
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        {
                            if (text.text.isNotEmpty()) {
                                pengingat.add(0, text.text)
                                text = TextFieldValue("")
                            } else {
                                isPressed = !isPressed
                            }
                        },
                        modifier = Modifier
                            .width(47.dp)
                            .height(45.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (text.text.isNotEmpty()) kirim[1] else kirim[0]
                            ),
                            contentDescription = if (text.text.isNotEmpty()) "tambah pengingat" else "btnpengingat",
                            modifier = Modifier
                                .width(47.dp)
                                .height(45.dp)
                        )
                    }
                }
            }
        }
    }

    // Menampilkan DatePickerDialog saat openDialog bernilai true
    if (openDialog.value) {
        var datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Menambahkan tanggal yang dipilih ke pengingat
                        val selectedDate = Tools.convertLongToTime(datePickerState.selectedDateMillis!!)
                        pengingat[0] = "${pengingat[0]} - $selectedDate" // Mengupdate pesan pengingat yang dipilih
                        openDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openDialog.value = false }
                ) {
                    Text("Batal")
                }
            },

            )
        {
            DatePicker(state = datePickerState)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 30.dp, top = 50.dp)
                ) {
                    IconButton(
                        onClick = { navHostController.navigate("detailplant_screen") },
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.btnback),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "Jadwal",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 20.dp)
                    )
                }
            }
        }
        //KALENDER

        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.kalender),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable { openDialog.value = true }
            )
        }
    }
}