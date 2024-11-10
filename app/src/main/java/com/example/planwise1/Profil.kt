package com.example.planwise1

import android.provider.ContactsContract.Profile
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile(){
    LazyColumn(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize(),
        horizontalAlignment = Alignment.Start)
    {

        item {
            Column(modifier = Modifier
                .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                Box(modifier = Modifier
                    .fillMaxWidth())
                {

                    Image(
                        modifier = Modifier
                            .width(412.dp)
                            .height(342.dp),
                        painter = painterResource(
                            id = R.drawable.wall1),
                        contentDescription = null)

                    IconButton(onClick = { /*TODO()*/},
                        modifier = Modifier
                            .width(125.dp)
                            .height(125.dp)
                            .align(Alignment.BottomCenter)
                            .offset(y = 53.dp),)
                    {
                        Image(modifier = Modifier.size(150.dp),
                            painter = painterResource(
                                id = R.drawable.profile1),
                            contentDescription = null)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 75.dp, start = 45.dp)
                    ) {
                        IconButton(onClick = { /*TODO()*/},
                            modifier = Modifier.width(50.dp) .height(50.dp)) {
                            Image(
                                modifier = Modifier
                                    .width(41.dp)
                                    .height(41.dp),
                                painter = painterResource(id = R.drawable.btnkembali),
                                contentDescription = "Back button"
                            )
                        }
                        Text(
                            text = "Profil",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }

                }

                Text(
                    text = "Syahri Ghifari M",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 70.dp)
                )
                Text(
                    text = "syahrighifari012@gmail.com",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 5.dp)
                )

            }
        }

        item {

            Text(
                text = "Bio",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 50.dp, top = 50.dp)
            )
            Text(
                text = "Mahasiswa Universitas Indonesia||Teknik Informatika||Hobi Koleksi Tanaman Hias",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(start = 50.dp, top = 15.dp, end = 50.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))
            Image(modifier = Modifier.width(350.dp)
                .height(2.dp)
                .padding(start = 55.dp),
                painter = painterResource(
                    id = R.drawable.garis),
                contentDescription = null)

        }
        item {
            Column(modifier = Modifier
                .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    text = "Akun",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 50. dp, top = 15.dp, end = 50.dp)
                )
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 40.dp, top = 10.dp),
                )
            {

                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier.width(50.dp) .height(50.dp)) {
                    Image(
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp),
                        painter = painterResource(id = R.drawable.iconprofile),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Data Pribadi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)) {
                    Image(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.iconpanah),
                        contentDescription = null
                    )
                }
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 40.dp, top = 10.dp),
            )
            {

                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier.width(50.dp) .height(50.dp)) {
                    Image(
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp),
                        painter = painterResource(id = R.drawable.iconsetting),
                        contentDescription = null
                    )
                }
                Text(
                    text = "Pengaturan",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)) {
                    Image(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.iconpanah),
                        contentDescription = null
                    )
                }
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 40.dp, top = 10.dp),
            )
            {

                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier.width(50.dp) .height(50.dp)) {
                    Image(
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp),
                        painter = painterResource(id = R.drawable.iconmyplant),
                        contentDescription = null
                    )
                }
                Text(
                    text = "MyPlant",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)) {
                    Image(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.iconpanah),
                        contentDescription = null
                    )
                }
            }
        }
        item {
            Column(modifier = Modifier
                .background(color = Color.White)
                .padding(start = 45.dp, top = 70.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                IconButton(
                    onClick = { /*TODO()*/ },
                    modifier = Modifier
                        .width(327.dp)
                        .height(57.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .width(327.dp)
                            .height(52.dp),
                        painter = painterResource(id = R.drawable.btnlogout),
                        contentDescription = null
                    )
                }
            }
        }
    }
}