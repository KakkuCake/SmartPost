package com.example.smartpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingActivity extends AppCompatActivity {

    private Button buttonF, buttonE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);


        buttonF = findViewById(R.id.buttonF);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityF();
            }
        });

        buttonE = findViewById(R.id.buttonE);
        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityE();
            }
        });
    }

    public void startActivityF() {
        Intent intent = new Intent(StartingActivity.this, SmartpostFinland.class);
        startActivity(intent);
    }

    public void startActivityE() {
        Intent intent = new Intent(StartingActivity.this, SmartpostEstonia.class);
        startActivity(intent);
    }
}
