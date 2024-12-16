package com.example.planwise1

import CameraScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planwise1.ui.theme.Planwise1Theme
import com.example.planwise1.DataStoreManager
import com.example.planwise1.viewmodel.PlantsViewModel
import com.example.planwise1.viewmodel.PlantsViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        databaseHelper = DatabaseHelper(this)
        dataStoreManager = DataStoreManager(this)
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase

        setContent {
            Planwise1Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val context = LocalContext.current

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
                        composable(
                            "list_screen"
                        ) {
                            val viewModel: PlantsViewModel = viewModel(factory = PlantsViewModelFactory(context = context))
                            ListKamusScreen(navController, viewModel = viewModel)
                        }
                        composable("profil_screen") { Profile(navController) }
                        composable("myplant_screen") { MyPlantScreen(navController) }
                        composable("detailplant_screen") {
                            DetailMyPlant(navController, dataStoreManager)
                        }
                        composable("detailplant2_screen") {
                            DetailMyPlant2(navController, dataStoreManager)
                        }
                        composable("detailplant3_screen") { DetailMyPlant3(navController) }
                        composable("jadwal_screen") { JadwalScreen(navController) }
                        composable("camera_screen") { CameraScreen(navController) }
                    }
                }
            }
        }
    }
}
