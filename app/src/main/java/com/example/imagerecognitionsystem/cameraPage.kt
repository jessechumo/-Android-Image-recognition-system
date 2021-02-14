package com.example.imagerecognitionsystem

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.imagerecognitionsystem.cameraPage
import com.example.imagerecognitionsystem.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.IOException

class cameraPage : AppCompatActivity() {
    lateinit var bitmap: Bitmap


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


        var tv: TextView = findViewById(R.id.textView21) //initializing textView21 as tv
        val fileName = "label.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use {it.readText()}
        var townList = inputString.split("\n")// initializing townList variable.



        // Adding the functionality for the predict button
        var predict: Button = findViewById(R.id.button3)//declaring the predict button
        predict.setOnClickListener(View.OnClickListener{
            var resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 224,224, true)// resizing the bitmap to size of prediction model.
            val model = MobilenetV110224Quant.newInstance(this)

// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)

            var tbuffer = TensorImage.fromBitmap(resized)
            var byteBuffer = tbuffer.buffer
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer //Output prediction exists.

            var max = getMax(outputFeature0.floatArray)

            tv.setText(townList[max]) // no need for toString() since it is already a string.

// Releases model resources if no longer used.
            model.close()
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            bitmap = BitmapFactory.decodeFile(currentPhotoPath) // removing val from bitmap variable.
           // var uri: Uri?= data?.data
            //bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            val imageView = findViewById<ImageView?>(R.id.imageView21) // declaring imageview21 as imageView
            if (imageView != null) {
                imageView.setImageBitmap(bitmap)
            }
        }
    }
    fun getMax(arr:FloatArray): Int{
        var ind = 0
        var min = 0.0f

        for(i in 0..1000) {
            if(arr[i]>min)
            {
                ind = i
                min = arr[i]
            }
        }
        return ind //index at the highest value
    }
}