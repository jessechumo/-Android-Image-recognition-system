package com.example.imagerecognitionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button getStarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStarted = (Button) findViewById(R.id.getStarted);
        getStarted.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMainApp();
            }

        });
    }

    public void  openMainApp() {
        Intent intent = new Intent(this, mainAppPage.class);
        startActivity(intent);
    }
}