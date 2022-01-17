package com.example.testactivityandroid_9.ui.login.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.testactivityandroid_9.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login_Signup_AgreementActivity extends AppCompatActivity {

    ImageButton imageButton;
    @BindView(R.id.textViewAgreement)
    TextView textViewAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_agreement);

        ButterKnife.bind(this);

        clickBtnBack();

        textViewAgreement.setMovementMethod(new ScrollingMovementMethod());
    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}