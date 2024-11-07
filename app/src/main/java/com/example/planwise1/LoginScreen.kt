package com.example.planwise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button
        IconButton(onClick = { /* handle back action */ }) {
            Icon(Icons.Default.Close, contentDescription = "Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Welcome message
        Text(
            text = "Selamat Datang!\nMari Merawat Bersama",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D253C),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Username TextField
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Masukkan Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Masukkan Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password
        TextButton(onClick = { /* handle forgot password */ }) {
            Text("Lupa Password?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = { /* handle login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Masuk", color = Color(0xFF0D253C))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Remember me switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF0D253C))
            )
            Text("Tetap Login")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login with divider
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
            Text("Masuk dengan")
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Social media login icons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* handle Facebook login */ }) {
                Image(
                    painter = painterResource(id = R.drawable.fb_icon), // Replace with actual icon resource
                    contentDescription = "Facebook"
                )
            }
            IconButton(onClick = { /* handle Google login */ }) {
                Image(
                    painter = painterResource(id = R.drawable.google_icon), // Replace with actual icon resource
                    contentDescription = "Google"
                )
            }
            IconButton(onClick = { /* handle GitHub login */ }) {
                Image(
                    painter = painterResource(id = R.drawable.github_icon), // Replace with actual icon resource
                    contentDescription = "GitHub"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Register prompt
        TextButton(onClick = { /* handle register */ }) {
            Text("Belum Punya Akun? Daftarkan", color = Color.Gray)
        }
    }
}

