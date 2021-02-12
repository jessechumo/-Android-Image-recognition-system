package com.example.imagerecognitionsystem

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.imagerecognitionsystem.cameraPage
import java.io.File
import java.io.IOException

class cameraPage : AppCompatActivity() {
    private var currentPhotoPath: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_page)
        findViewById<View?>(R.id.captureImage1)?.setOnClickListener(View.OnClickListener {
            val fileName = "photo"
            val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            try {
                val imageFile = File.createTempFile(fileName, ".jpg", storageDirectory)
                currentPhotoPath = imageFile.absolutePath
                val imageUri = FileProvider.getUriForFile(this@cameraPage, "com.example.imagerecognitionsystem.fileprovider", imageFile)
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(intent, 1)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(currentPhotoPath)
            val imageView = findViewById<ImageView?>(R.id.imageView21)
            if (imageView != null) {
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}