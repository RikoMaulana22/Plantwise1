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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.planwise1.viewmodel.PlantsViewModel

@Composable
fun ListKamusScreen(navHostController: NavHostController, viewModel: PlantsViewModel){
    var plant by remember { mutableStateOf(TextFieldValue("")) }
    val speciesList by viewModel.speciesList.observeAsState(emptyList())

    LaunchedEffect(key1 = Unit) {
        viewModel.getSpeciesList()
    }
    val nameplant = listOf(
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
        "Kaktus badag\n(catevalus)",
    )

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0XFFE9EFEC)),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 117.dp)
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
                        IconButton(onClick = { /*TODO*/ })
                        {
                            Image(
                                painter = painterResource(id = R.drawable.iconsearch),
                                contentDescription = "Search Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

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
                        contentDescription = null
                    )
                }
            }
        }
        items(speciesList.take(40)) { item ->
            key(item.id) {
                Text(
                    text = item.commonName ?: "No Data",
                    color = Color.Black, // Warna teks hitam
                    fontSize = 16.sp, // Ukuran font opsional
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp) // Spasi dari tepi
                        .fillMaxWidth() // Pastikan elemen teks memenuhi lebar
                )
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
                    IconButton(onClick = { navHostController.navigate("kamus_screen")},
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
                        text = "Kamus Tanaman",
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