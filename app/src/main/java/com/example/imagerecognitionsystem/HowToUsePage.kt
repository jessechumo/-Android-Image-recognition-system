package com.example.imagerecognitionsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HowToUsePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_use_page)
        val backarrow = findViewById<View?>(R.id.imageView7) as ImageView?
        if (backarrow != null) {
            backarrow.setOnClickListener(View.OnClickListener { headBack() })
        }
    }

    fun headBack() {
        val intent = Intent(this, mainAppPage::class.java)
        startActivity(intent)
    }
}