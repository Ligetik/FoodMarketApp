package com.example.testactivityandroid_9.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.ui.login.signup.Login_SignupActivity;

public class LogInActivity extends AppCompatActivity {
    ImageButton imageButton;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clickBtnBack();

        clickBtnRegistration();
    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed();
            }
        });
    }
    private void clickBtnRegistration() {
        regButton = (Button) findViewById(R.id.registration);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login_SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}