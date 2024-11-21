package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController, databaseHelper: DatabaseHelper) {
    // State variables for username, password, and error handling
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Button states
    val btnmasuk = listOf(
        R.drawable.btnmasuk,
        R.drawable.btnmasuk2
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button to navigate to onboarding
        IconButton(
            onClick = { navHostController.popBackStack() },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp, top = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.btnback),
                contentDescription = "Back Button",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Welcome text
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

        // Username input field
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

        // Password input field
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
                    val icon = if (passwordVisibility) R.drawable.notshowpw else R.drawable.showpw
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Toggle password visibility",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Error message
        if (loginError.isNotEmpty()) {
            Text(
                text = loginError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show loading indicator if logging in
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color(0xFF16423C)
            )
        }

        // Login button
        IconButton(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    loginError = "Username dan Password tidak boleh kosong"
                } else {
                    isLoading = true
                    loginError = ""
                    // Simulating login process
                    val isLoginSuccessful = databaseHelper.checkUser(username, password)
                    isLoading = false
                    if (isLoginSuccessful) {
                        // Arahkan ke halaman Komunitas setelah login berhasil
                        navHostController.navigate("beranda_screen")
                    } else {
                        loginError = "Username atau Password salah"
                    }
                }
            },
            modifier = Modifier
                .width(370.dp)
                .height(70.dp)
        ) {
            Image(
                painter = painterResource(id = btnmasuk[0]),
                contentDescription = "Login Button",
                modifier = Modifier
                    .width(344.dp)
                    .height(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Remember me switch
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp)
        ) {
            Switch(
                modifier = Modifier.padding(end = 5.dp),
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF16423C))
            )
            Text("Tetap Login", color = Color(0xFF6A707C))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Register navigation
        TextButton(onClick = { navHostController.navigate("register_screen") }) {
            Text("Belum Punya Akun? Daftarkan", color = Color.Gray)
        }
    }
}
