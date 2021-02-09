package com.example.imagerecognitionsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HowToUsePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use_page);

        ImageView backarrow = (ImageView) findViewById(R.id.imageView7);
        backarrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                headBack();
            }
        });

    }

    public void  headBack() {
        Intent intent = new Intent(this, mainAppPage.class);
        startActivity(intent);
    }
}