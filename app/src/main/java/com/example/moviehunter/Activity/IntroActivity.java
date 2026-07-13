package com.example.moviehunter.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import com.example.moviehunter.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        AppCompatButton getInBtn = findViewById(R.id.getInBtn);
        getInBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login_Activity.class);
            startActivity(intent);
            finish();
        });
    }
}