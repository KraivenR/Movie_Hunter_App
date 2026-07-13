package com.example.moviehunter.Activity;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.widget.Toast;

import com.example.moviehunter.R;


public class Login_Activity extends AppCompatActivity {
    private EditText userEdit;
    private EditText passwordEdit;
    private AppCompatButton logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }
    private void initView(){
            userEdit = findViewById(R.id.userEdit);
            passwordEdit = findViewById(R.id.passwordEdit);
            logInBtn = findViewById(R.id.logInBtn);

            logInBtn.setOnClickListener(v -> {
                if (userEdit.getText().toString().isEmpty() || passwordEdit.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please fill in your credentials", Toast.LENGTH_SHORT).show();
                } else if (userEdit.getText().toString().equals("admin") && passwordEdit.getText().toString().equals("admin")) {


                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
}
}