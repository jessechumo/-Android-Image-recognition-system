package com.example.imagerecognitionsystem

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.renderscript.Element
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.imagerecognitionsystem.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.schema.TensorType.UINT8
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class GalleryImport : AppCompatActivity() {

    // Initializing the variables for bitmap & imageview
    lateinit var bitmap: Bitmap
    lateinit var imgview: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_import)

        imgview= findViewById(R.id.imageView25)
        val fileName = "label.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use {it.readText()}
        var townList = inputString.split("\n")

        var tv:TextView = findViewById(R.id.textView2)

        var select:Button = findViewById(R.id.button) //functionality of the select button.
        select.setOnClickListener(View.OnClickListener {
            var intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent,  100)

        })
        var predict:Button = findViewById(R.id.button2) //functionality for predict button
        predict.setOnClickListener(View.OnClickListener {
            var resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 224,224, true)// resizing the bitmap to size of prediction model.
            val model = MobilenetV110224Quant.newInstance(this)

// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)

            var tbuffer = TensorImage.fromBitmap(resized)
            var byteBuffer = tbuffer.buffer
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer //Output prediction exists

            var max = getMax(outputFeature0.floatArray)

            tv.setText(townList[max])

// Releases model resources if no longer used.
            model.close()

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imgview.setImageURI(data?.data)

        var uri: Uri?= data?.data
        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
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

