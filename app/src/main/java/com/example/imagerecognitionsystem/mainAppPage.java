package com.example.imagerecognitionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainAppPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_page);

        Button about = (Button) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMainApp();
            }

        });

        Button howToUse = (Button) findViewById(R.id.howToUse);
        howToUse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHowToUse();
            }

        });

        Button takePicture = (Button) findViewById(R.id.takePhoto);
        takePicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCamera();
            }

        });
    }

    public void  openMainApp() {
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }


    public void  openHowToUse() {
        Intent intent = new Intent(this, HowToUsePage.class);
        startActivity(intent);
    }

    public void  openCamera() {
        Intent intent = new Intent(this, cameraPage.class);
        startActivity(intent);
    }
}





