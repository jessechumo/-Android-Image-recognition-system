package com.example.imagerecognitionsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)
        val backarrow = findViewById<View?>(R.id.imageView8) as ImageView?
        if (backarrow != null) {
            backarrow.setOnClickListener(View.OnClickListener { headBack() })
        }
    }

    fun headBack() {
        val intent = Intent(this, mainAppPage::class.java)
        startActivity(intent)
    }
}