package com.example.imagerecognitionsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var getStarted: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getStarted = findViewById<View?>(R.id.getStarted) as Button?
        getStarted?.setOnClickListener(View.OnClickListener { openMainApp() })
    }

    fun openMainApp() {
        val intent = Intent(this, mainAppPage::class.java)
        startActivity(intent)
    }
}