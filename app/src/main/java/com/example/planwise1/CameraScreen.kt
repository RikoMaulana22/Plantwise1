import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.planwise1.R

@Composable
fun CameraScreen(navHostController: NavHostController) {
    var isChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasPermission = isGranted
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            // Handle selected image URI here
            // You can load it into an Image or process it further
            println("Selected image URI: $uri")
        }
    }

    val captureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            // Handle the captured image bitmap here
            println("Captured bitmap: $bitmap")
        }
    }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (hasPermission) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            // Camera Preview
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = androidx.camera.core.Preview.Builder().build()
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    preview.setSurfaceProvider(previewView.surfaceProvider)

                    cameraProvider.bindToLifecycle(
                        ctx as androidx.lifecycle.LifecycleOwner,
                        cameraSelector,
                        preview
                    )
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )

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
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { navHostController.popBackStack() }
                )
            }

            // Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Gray)
                    .padding(top = 10.dp, start = 20.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.btngaleri),
                        contentDescription = "icon galeri",
                        modifier = Modifier
                            .size(45.dp)
                            .clickable {
                                galleryLauncher.launch("image/*")
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.btncapture),
                        contentDescription = "icon capture",
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                captureLauncher.launch()
                            }
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AnimatedCustomSwitch(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            checkedImage = painterResource(id = R.drawable.btnpenyakit),
                            uncheckedImage = painterResource(id = R.drawable.btntanaman)
                        )
                        Text(
                            text = "Ganti Mode",
                            fontSize = 15.sp,
                        )
                    }
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Camera permission is required to use this feature.")
        }
    }
}

@Composable
fun AnimatedCustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedImage: Painter,
    uncheckedImage: Painter
) {
    // Animasi untuk posisi toggle
    val toggleOffset by animateFloatAsState(
        targetValue = if (checked) 20f else 0f,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .width(50.dp)
            .height(30.dp)
            .background(
                if (checked) Color(0xFF6A9C89) else Color(0xFFD1D1D1),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onCheckedChange(!checked) }
            .padding(4.dp), // Padding untuk ruang toggle
        contentAlignment = Alignment.CenterStart
    ) {
        // Gambar toggle dengan animasi posisi
        Image(
            painter = if (checked) checkedImage else uncheckedImage,
            contentDescription = null,
            modifier = Modifier
                .size(22.dp)
                .offset(x = toggleOffset.dp)
        )
    }
}


