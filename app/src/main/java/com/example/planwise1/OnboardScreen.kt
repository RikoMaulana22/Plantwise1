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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardSreen(navHostController: NavHostController){

    val imageList = listOf(
        R.drawable.onboardsatuuu,
        R.drawable.onboarddua,
        R.drawable.onboardtiga,
    )
    val sliderimage = listOf(
        R.drawable.slidersatu,
        R.drawable.sliderdua,
        R.drawable.slidertiga,
    )
    val copywriter = listOf(
        "Selamat Datang di PlantWise!",
        "Bergabung dengan Komunitas Pecinta Tanaman",
        "Fitur Canggih untuk Deteksi Penyakit Tanaman-Mu",
    )
    val copywriter2 = listOf(
        "Mari bersama-sama merawat keindahan alam." +
                " Temukan ribuan tanaman hijau yang siap mempercantik rumahmu. " +
                "Mulai petualangan berkebunmu sekarang!",
        "Temukan tips, inspirasi, dan teman baru sesama pecinta tanaman! Yuk, " +
                "gabung sekarang dan tumbuhkan kebun impian bersama!",
        "Scan tanamanmu untuk mendeteksi penyakit dengan cepat dan akurat"
    )

    val pagerState = rememberPagerState()

    Column(modifier = Modifier
        .background(color = Color.Yellow)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White))
        {

            HorizontalPager(
                count = imageList.size,
                state = pagerState,
                modifier = Modifier
                    .width(412.dp)
                    .height(917.dp)
            ) { page ->
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }


            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .align(Alignment.Center))
            {

                Image(
                    modifier = Modifier
                        .width(79.dp)
                        .height(16.dp)
                        .align(Alignment.Center),
                    painter = painterResource(
                        id = sliderimage[pagerState.currentPage]
                    ),
                    contentDescription = null
                )

            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center))
            {

                Text(
                    text = copywriter[pagerState.currentPage],
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 100.dp)
                )

            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .align(Alignment.Center))
            {

                Text(
                    text = copywriter2[pagerState.currentPage],
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 250.dp)
                        .align(Alignment.Center)
                )

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {

                IconButton(onClick = { navHostController.navigate("logins_screen")},
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
                IconButton(onClick = { navHostController.navigate("register_screen")},
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