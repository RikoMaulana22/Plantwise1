package com.example.planwise1

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun RegistrationScreen(navHostController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dbHelper = remember { DatabaseHelper(context) }

    val btndaftar = listOf(
        R.drawable.btndaftar,
        R.drawable.btndaftar2
    )

    // State variables for error messages
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    var isClicked by remember { mutableStateOf(false) }

    // Regex pattern for validating email
    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    // Function to validate fields
    fun validateFields(): Boolean {
        var isValid = true

        // Clear previous errors
        usernameError = ""
        emailError = ""
        passwordError = ""
        confirmPasswordError = ""

        if (username.isEmpty()) {
            usernameError = "Username harus diisi"
            isValid = false
        }

        if (email.isEmpty()) {
            emailError = "Email harus diisi"
            isValid = false
        } else if (!email.matches(emailPattern.toRegex())) {
            emailError = "Format email tidak valid"
            isValid = false
        }

        if (password.isEmpty()) {
            passwordError = "Password harus diisi"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordError = "Konfirmasi password harus diisi"
            isValid = false
        }

        return isValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { navHostController.navigate("onboarding_screen") },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp, top = 50.dp)
                .width(50.dp)
                .height(50.dp)
        ) {
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
            text = "Halo!\n \nDaftarkan Sekarang",
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
                .padding(start = 30.dp, end = 30.dp),
            isError = usernameError.isNotEmpty()
        )

        // Error message for username
        if (usernameError.isNotEmpty()) {
            Text(
                text = usernameError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Masukkan Email") },
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            isError = emailError.isNotEmpty()
        )

        // Error message for email
        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

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
                    val icon = if (passwordVisibility) {
                        painterResource(id = R.drawable.notshowpw)
                    } else {
                        painterResource(id = R.drawable.showpw)
                    }
                    Icon(
                        painter = icon, contentDescription = "Toggle password visibility",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            isError = passwordError.isNotEmpty()
        )

        // Error message for password
        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password input field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Konfirmasi Password") },
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
                    Icon(
                        painter = icon, contentDescription = "Toggle password visibility",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            isError = confirmPasswordError.isNotEmpty()
        )

        // Error message for confirm password
        if (confirmPasswordError.isNotEmpty()) {
            Text(
                text = confirmPasswordError,
                color = Color.Red,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register button
        IconButton(
            onClick = {
                if (validateFields()) {
                    if (password == confirmPassword) {
                        val isRegistered = dbHelper.registerUser(username, email, password)
                        if (isRegistered) {
                            navHostController.navigate("logins_screen")
                        } else {
                            Toast.makeText(context, "Gagal mendaftar. Coba lagi.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .width(370.dp)
                .height(70.dp)
        ) {
            Image(
                painter = painterResource(id = if (isClicked) btndaftar[1] else btndaftar[0]),
                contentDescription = "cari btn",
                modifier = Modifier
                    .width(344.dp)
                    .height(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        TextButton(onClick = { navHostController.navigate("logins_screen") }) {
            Text("Sudah Punya Akun? Masuk", color = Color.Gray)
        }
    }
}

