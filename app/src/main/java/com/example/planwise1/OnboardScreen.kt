package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardSreen(){
    Column(modifier = Modifier
        .background(color = Color.Yellow)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White))
        {

            Image(
                modifier = Modifier
                    .width(412.dp)
                    .height(917.dp),
                painter = painterResource(
                    id = R.drawable.onboardsatuuu),
                contentDescription = null)

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = 167.dp,top = 475.dp))
            {

                Image(
                    modifier = Modifier
                        .width(79.dp)
                        .height(16.dp),
                    painter = painterResource(
                        id = R.drawable.slidersatu),
                    contentDescription = null)

            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = 55.dp, top = 518.dp))
            {

                Text(
                    text = "Selamat Datang di PlantWise!",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 20.dp)
                )

            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, top = 551.dp))
            {

                Text(
                    text = "Mari bersama-sama merawat keindahan alam. " +
                            "Temukan ribuan tanaman hijau yang siap mempercantik rumahmu. " +
                            "Mulai petualangan berkebunmu sekarang!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 742.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {

                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier.width(370.dp) .height(39.dp)) {
                    Image(
                        modifier = Modifier
                            .width(344.dp)
                            .height(39.dp),
                        painter = painterResource(id = R.drawable.btnpunya),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(9.dp))
                IconButton(onClick = { /*TODO()*/},
                    modifier = Modifier.width(370.dp) .height(39.dp)) {
                    Image(
                        modifier = Modifier
                            .width(344.dp)
                            .height(39.dp),
                        painter = painterResource(id = R.drawable.btngak),
                        contentDescription = null
                    )
                }

            }

        }

    }
}