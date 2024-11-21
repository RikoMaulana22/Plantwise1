package com.example.planwise1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planwise1.ui.theme.Planwise1Theme

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        databaseHelper = DatabaseHelper(this)

        setContent {
            Planwise1Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "onboarding_screen"
                    ) {
                        composable("onboarding_screen") { OnboardSreen(navController) }
                        composable("logins_screen") { LoginScreen(navController, databaseHelper) }
                        composable("register_screen") { RegistrationScreen(navController) }
                        composable("beranda_screen") { Beranda(navController) }
                        composable("komunitas_screen") { KomunitasScreen(navController) }
                        composable("kamus_screen") { KamusTanamanScreen(navController) }
                        composable("list_screen") { ListKamusScreen(navController) }
                        composable("profil_screen") { Profile(navController) }
                        composable("myplant_screen") { MyPlantScreen(navController) }
                    }
                }
            }
        }
    }
}
