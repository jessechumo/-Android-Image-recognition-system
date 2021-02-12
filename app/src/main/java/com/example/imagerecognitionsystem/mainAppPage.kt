package com.example.imagerecognitionsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class mainAppPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app_page)

        val about = findViewById<View?>(R.id.about) as Button?
        if (about != null) {
            about.setOnClickListener(View.OnClickListener { openMainApp() })
        }

        val howToUse = findViewById<View?>(R.id.howToUse) as Button?
        if (howToUse != null) {
            howToUse.setOnClickListener(View.OnClickListener { openHowToUse() })
        }

        val takePicture = findViewById<View?>(R.id.takePhoto) as Button?
        if (takePicture != null) {
            takePicture.setOnClickListener(View.OnClickListener { openCamera() })
        }

        val galleryImports = findViewById<View?>(R.id.importFromGallery) as Button?
        if (galleryImports != null) {
            galleryImports.setOnClickListener(View.OnClickListener { openGalleryPage() })
        }

    }

    fun openMainApp() {
        val intent = Intent(this, AboutPage::class.java)
        startActivity(intent)
    }

    fun openHowToUse() {
        val intent = Intent(this, HowToUsePage::class.java)
        startActivity(intent)
    }

    fun openCamera() {
        val intent = Intent(this, cameraPage::class.java)
        startActivity(intent)
    }

    fun openGalleryPage() {
        val intent = Intent(this, GalleryImport::class.java)
       startActivity(intent)
    }
}