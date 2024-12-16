package com.example.planwise1

import android.net.Uri
import android.provider.ContactsContract.Profile
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.planwise1.data.SharedPrePreferencesManager

@Composable
fun Profile(navHostController: NavHostController){
    val dbHelper = DatabaseHelper(LocalContext.current)
    val context = LocalContext.current
    val sharedPreferencesManager = SharedPrePreferencesManager(context)
    // Ambil data pengguna
    val username = sharedPreferencesManager.name ?: "Pengguna"
    val email = sharedPreferencesManager.email ?: "email"
    val wall = sharedPreferencesManager.wall ?: "default_wall_uri" // Ganti default_wall_uri dengan gambar default
    val profile = sharedPreferencesManager.profil ?: "default_profile_uri"


    var wallImageUri by remember { mutableStateOf(sharedPreferencesManager.wall ?: "default_wall_uri") }
    var profileImageUri by remember { mutableStateOf(sharedPreferencesManager.profil ?: "default_profile_uri") }


    var currentSelecting by remember { mutableStateOf("wall") }
    val userId: Int? = sharedPreferencesManager.getUserId() // Mengambil userId dari SharedPreferences

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            if (currentSelecting == "wall") {
                wallImageUri = it.toString()
                sharedPreferencesManager.wall = wallImageUri // Simpan gambar wall ke SharedPreferences
                userId?.let { id ->
                    dbHelper.updateUserWallImage(id, wallImageUri) // Update gambar wall di database
                }
            } else {
                profileImageUri = it.toString()
                sharedPreferencesManager.profil = profileImageUri // Simpan gambar profil ke SharedPreferences
                userId?.let { id ->
                    dbHelper.updateUserProfileImage(id, profileImageUri) // Update gambar profil di database
                }
            }
        } ?: run {
            Log.e("Profile", "Gagal memilih gambar")
        }
    }

    // State for showing the logout confirmation dialog
    var showLogoutDialog by remember { mutableStateOf(false) }

    fun logout() {
        // Menghapus data yang disimpan di SharedPreferences
        sharedPreferencesManager.clear() // Pastikan Anda menambahkan fungsi clear di SharedPreferencesManager

        // Navigasi ke layar login setelah logout
        navHostController.navigate("logins_screen") // Ubah ke rute layar login Anda
    }



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

                Box(modifier = Modifier.fillMaxWidth()) {
                    // Gambar Wall
                    Image(
                        modifier = Modifier
                            .width(412.dp)
                            .height(342.dp)
                            .clickable {
                                currentSelecting = "wall"
                                galleryLauncher.launch("image/*")
                            },
                        painter = rememberAsyncImagePainter(wallImageUri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    // Gambar Profil
                    IconButton(
                        onClick = {
                            currentSelecting = "profile"
                            galleryLauncher.launch("image/*")
                        },
                        modifier = Modifier
                            .width(125.dp)
                            .height(125.dp)
                            .align(Alignment.BottomCenter)
                            .offset(y = 53.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(150.dp),
                            painter = rememberAsyncImagePainter(profileImageUri),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 75.dp, start = 45.dp)
                    ) {
                        IconButton(onClick = { navHostController.navigate("beranda_screen")},
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
                    text = "$username",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 70.dp)
                )
                Text(
                    text = "$email",
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
                    onClick = { showLogoutDialog = true },
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
    // Logout Confirmation Dialog
    if (showLogoutDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Apakah Anda Yakin Ingin Keluar?") },
            confirmButton = {
                TextButton(onClick = {
                    showLogoutDialog = false
                    logout()
                }) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Tidak")
                }
            }
        )
    }
}