package com.deliverroo

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.deliverroo.ui.theme.DeliverrooTheme
import com.deliverroo.util.OpenCVHelper
import org.opencv.android.OpenCVLoader
import org.opencv.imgproc.Imgproc
import java.io.File


class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            readPermissionGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: readPermissionGranted
            writePermissionGranted = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: writePermissionGranted
        }
        updateOrRequestPermissions()

        OpenCVLoader.initDebug() // crucial for openCV to work
        val filePath = "sdcard/Download/ocrimages/a.jpg"
        val baseImageBitmap: Bitmap = BitmapFactory.decodeFile(filePath)

        var ocrSwitch = false

        //var bitmap = OpenCVHelper.AdaptiveThreshToBitmap(baseImageBitmap,1,255.0,11,12.0)
        //bitmap = OpenCVHelper.erodeToBitmap(bitmap)
        //OpenCVHelper.dynamicResize(filePath,0.0,0.0)


        if (ocrSwitch) {
            val ocr = OcrRunnable(this,baseImageBitmap)
            ocr.run()
            val result = ocr.result
            Log.d("OCR", result)
        }

        /*
        Runs preprocessing algos to find which combination of them gives the best ocr results
         */

        var resultList = OpenCVHelper.getOptimalResizing(baseImageBitmap, this)
        //var resultList: MutableList<Any> = getOptimalImagePreProcessing(baseImageBitmap)
        //Log.d("Phone", resultList.toString())



        setContent {
            DeliverrooTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
    private fun updateOrRequestPermissions() {
        val hasReadPermission = ContextCompat.checkSelfPermission(
            this,Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        val hasWritePermission = ContextCompat.checkSelfPermission(
            this,Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        readPermissionGranted = hasReadPermission
        writePermissionGranted = hasWritePermission || minSdk29

        val permissionsToRequest = mutableListOf<String>()
        if(!writePermissionGranted) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!readPermissionGranted) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(permissionsToRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeliverrooTheme {
        Greeting("Android")
    }
}










