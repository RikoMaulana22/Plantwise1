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
fun OnboardScreen2(){
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
                    id = R.drawable.onboarddua),
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
                        id = R.drawable.sliderdua),
                    contentDescription = null)

            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = 518.dp))
            {

                Text(
                    text = "Bergabung dengan Komunitas Pecinta Tanaman",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, top = 570.dp))
            {

                Text(
                    text = "Temukan tips, inspirasi, " +
                            "dan teman baru sesama pecinta tanaman! " +
                            "Yuk, gabung sekarang dan tumbuhkan kebun impian bersama!",
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