package com.example.testactivityandroid_9.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.testactivityandroid_9.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login_ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.userForgotEmail)
    EditText userForgotEmail;
    @BindView(R.id.btnResetPassword)
    Button btnResetPassword;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forgot_password);

        ButterKnife.bind(this);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.setLanguageCode("ru");

        clickBtnBack();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String UserEmail = userForgotEmail.getText().toString();

                if (TextUtils.isEmpty(UserEmail)) {
                    userForgotEmail.setError("Не указан электронный адрес");
                    return;
                }

                auth.sendPasswordResetEmail(UserEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login_ForgotPasswordActivity.this,
                                        "Ccылка с восстановлением пароля отправлена на почту",
                                        Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Login_ForgotPasswordActivity.this,
                                        "Ошибка", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }
        });
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