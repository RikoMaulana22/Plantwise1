import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.planwise1.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.IOException

fun uriToBitmap(context: android.content.Context, uri: android.net.Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)
        resizeBitmap(bitmap, 1024, 1024)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
    val aspectRatio = bitmap.width.toFloat() / bitmap.height
    val newWidth: Int
    val newHeight: Int

    if (aspectRatio > 1) {
        newWidth = maxWidth
        newHeight = (maxWidth / aspectRatio).toInt()
    } else {
        newHeight = maxHeight
        newWidth = (maxHeight * aspectRatio).toInt()
    }

    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CameraScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var selectedImageUri by remember { mutableStateOf<android.net.Uri?>(null) }
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var detectionResult by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) } // State untuk loading
    val coroutineScope = rememberCoroutineScope()

    // State untuk ModalBottomSheet
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasPermission = isGranted
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            selectedImageUri = it
            capturedBitmap = null
            detectionResult = null
        }
    }

    val captureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            capturedBitmap = it
            selectedImageUri = null
            detectionResult = null
        }
    }

    suspend fun detectDisease(bitmap: Bitmap?): String {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(context.cacheDir, "image.jpg")
                val outputStream = file.outputStream()
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.close()

                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                        "file", file.name,
                        RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
                    )
                    .build()

                val request = Request.Builder()
                    .url("https://image-detection.1osr27zdk703.us-south.codeengine.appdomain.cloud//predict-disease")
                    .post(requestBody)
                    .build()

                val client = OkHttpClient()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string() ?: return@withContext "No response body"
                    val json = org.json.JSONObject(responseBody)

                    val predictedClass = json.getString("predicted_class")
                    val cause = json.getString("cause")
                    val treatment = json.getString("treatment")

                    // Format informasi hasil deteksi
                    "Penyakit: $predictedClass\nPenyebab: $cause\nCara Mengatasi: $treatment"
                } else {
                    "Failed to detect disease: ${response.code}, ${response.message}"
                }
            } catch (e: Exception) {
                "Error: ${e.message}"
            }
        }
    }


    if (hasPermission) {
        val previewView = remember { PreviewView(context) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            if (selectedImageUri == null && capturedBitmap == null) {
                AndroidView(
                    factory = { previewView },
                    modifier = Modifier.fillMaxSize()
                ) {
                    val cameraProvider = cameraProviderFuture.get()
                    cameraProvider.unbindAll()
                    val preview = androidx.camera.core.Preview.Builder().build()
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    try {
                        preview.setSurfaceProvider(previewView.surfaceProvider)
                        cameraProvider.bindToLifecycle(
                            context as LifecycleOwner,
                            cameraSelector,
                            preview
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else {
                if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.5f))
                    )
                } else if (capturedBitmap != null) {
                    Image(
                        painter = BitmapPainter(capturedBitmap!!.asImageBitmap()),
                        contentDescription = "Captured Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.5f))
                    )
                }
            }

            // Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Gray)
                    .padding(top = 35.dp, start = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.btnexcamera),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { navHostController.popBackStack() }
                )
            }

            // Bottom Bar for Actions
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Gray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btngaleri),
                        contentDescription = "Gallery Icon",
                        modifier = Modifier
                            .size(45.dp)
                            .clickable { galleryLauncher.launch("image/*") }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.btncapture),
                        contentDescription = "Capture Icon",
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { captureLauncher.launch() }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.btnpenyakit),
                        contentDescription = "Detect Disease Icon",
                        modifier = Modifier
                            .size(45.dp)
                            .clickable {
                                coroutineScope.launch {
                                    isLoading = true // Mulai loading
                                    if (capturedBitmap != null) {
                                        detectionResult = detectDisease(capturedBitmap)
                                    } else if (selectedImageUri != null) {
                                        val bitmap = uriToBitmap(context, selectedImageUri!!)
                                        detectionResult = detectDisease(bitmap)
                                    }
                                    isLoading = false // Selesai loading
                                    bottomSheetState.show()
                                }
                            }
                    )
                }
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }

        // Bottom Sheet untuk Menampilkan Hasil Deteksi
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Hasil Deteksi Penyakit:",
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = detectionResult ?: "Menunggu hasil...",
                        color = Color.Black
                    )
                }
            }
        ) {

        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Izin kamera diperlukan untuk menggunakan fitur ini.")
            LaunchedEffect(Unit) {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}

