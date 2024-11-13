package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }

    val btnmasuk = listOf(
        R.drawable.btnmasuk,
        R.drawable.btnmasuk2
    )
    var isClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(onClick = { navHostController.navigate("onboarding_screen") },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp, top = 50.dp)
                .width(50.dp)
                .height(50.dp)) {
            Image(
                painter = painterResource(id = R.drawable.btnback),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp),
            text = "Selamat Datang!\n \nMari Merawat Bersama",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D253C),
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Masukkan Username") },
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Masukkan Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),
            trailingIcon = {

                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    val icon = if (passwordVisibility) {
                        painterResource(id = R.drawable.notshowpw)
                    } else {
                        painterResource(id = R.drawable.showpw)
                    }
                    Icon(painter = icon, contentDescription = "Toggle password visibility",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { /* handle forgot password */ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp))
        {
            Text("Lupa Password?",
                color = Color(0xFF6A707C)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        IconButton(
            onClick = { isClicked = !isClicked },
            modifier = Modifier
                .width(370.dp)
                .height(70.dp)
        ) {

            Image(
                painter = painterResource(id = if (isClicked) btnmasuk[1] else btnmasuk[0]),
                contentDescription = "cari btn",
                modifier = Modifier
                    .width(344.dp)
                    .height(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp)) {
            Switch(modifier = Modifier
                .padding(end = 5.dp),
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF16423C))
            )
            Text("Tetap Login",
                color = Color(0xFF6A707C)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
            Text("Masuk dengan",
                color = Color(0xFF6A707C)
            )
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* handle Facebook login */ },
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)) {
                Image(
                    modifier = Modifier
                        .width(105.dp)
                        .height(56.dp),
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    contentScale = ContentScale.Crop
                )
            }
            IconButton(onClick = { /* handle Google login */ },
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)) {
                Image(modifier = Modifier
                    .width(105.dp)
                    .height(56.dp),
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    contentScale = ContentScale.Crop
                )
            }
            IconButton(onClick = { /* handle GitHub login */ },
                modifier = Modifier
                    .width(130.dp)
                    .height(70.dp)) {
                Image(modifier = Modifier
                    .width(105.dp)
                    .height(56.dp),
                    painter = painterResource(id = R.drawable.github),
                    contentDescription = "GitHub",
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        TextButton(onClick = { navHostController.navigate("register_screen") }) {
            Text("Belum Punya Akun? Daftarkan", color = Color.Gray)
        }
    }
}



