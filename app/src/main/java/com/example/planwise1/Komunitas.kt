package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun KomunitasScreen(navHostController: NavHostController){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isPressed by remember { mutableStateOf(false) }
    val messages = remember { mutableStateListOf<String>() }

    val mic = listOf(
        R.drawable.micoff,
        R.drawable.micon,
    )
    val kirim = listOf(
        R.drawable.micoff,
        R.drawable.btnkirim,
    )

    Box(modifier = Modifier
        .fillMaxWidth())
    {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0XFFE9EFEC))
            .padding(bottom = 80.dp, top = 110.dp),
            reverseLayout = true,)
        {
            items(messages) { message ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Syahri Ghifari M",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(12.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Image(
                            painter = painterResource(id = R.drawable.profile1),
                            contentDescription = "Foto Profil",
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp))
                        )
                    }
                    Spacer(modifier = Modifier
                        .padding(top = 15.dp))
                    Text(
                        text = message,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .background(Color(0xFFFC4DAD2), RoundedCornerShape(8.dp))
                            .padding(15.dp)
                    )
                }
            }
        }
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
                                            text = "Ketik Pesan",
                                            style = TextStyle(color = Color.Gray, fontSize = 16.sp),
                                        )
                                    }
                                    innerTextField()
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Image(
                                    painter = painterResource(id = R.drawable.btnkamerakomu),
                                    contentDescription = "Icon 2",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    {
                        if (text.text.isNotEmpty()) {
                            messages.add(0, text.text)
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
                            id = if (text.text.isNotEmpty()) kirim[1] else mic[if (isPressed) 1 else 0]
                        ),
                        contentDescription = if (text.text.isNotEmpty()) "Icon Kirim" else "Icon Mic",
                        modifier = Modifier
                            .width(47.dp)
                            .height(45.dp)
                    )
                }
            }
        }
    }
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
                        text = "Komunitas",
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