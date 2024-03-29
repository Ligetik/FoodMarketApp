package com.example.testactivityandroid_9.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.testactivityandroid_9.MainActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.ui.login.signup.Login_SignupActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    ImageButton imageButton;
    Button regButton;
    private FirebaseAuth mAuth;


    @BindView(R.id.btnLogIn)
    Button btnLogIn;
    @BindView(R.id.btnForgotPassword)
    Button btnForgotPassword;
    @BindView(R.id.userLogInNumber)
    EditText userLogInNumber;
    @BindView(R.id.userLogInEmail)
    EditText userLogInEmail;
    @BindView(R.id.userLogInPassword)
    EditText userLogInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clickBtnBack();

        clickBtnRegistration();

        ButterKnife.bind(this);

        clickBtnLogIn();

        btnForgotPassword();


    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed();
            }
        });
    }

    public void clickBtnLogIn() {

        btnLogIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final String UserNumber = userLogInNumber.getText().toString().trim();
                final String UserEmail = userLogInEmail.getText().toString().trim();
                String UserPassword = userLogInPassword.getText().toString().trim();

                if (TextUtils.isEmpty(UserNumber)) {
                    userLogInNumber.setError("Не указан номер телефона");
                    return;
                }
                if (UserNumber.length() < 10) {
                    userLogInNumber.setError("Неккоректный номер телефона");
                    return;
                }
                if (TextUtils.isEmpty(UserEmail)) {
                    userLogInEmail.setError("Не указан электронный адрес");
                    return;
                }
                if (!isValidEmail(UserEmail)){
                    userLogInEmail.setError("Неверный формат электронной почты");
                    return;
                }
                if (TextUtils.isEmpty(UserPassword)) {
                    userLogInPassword.setError("Не указан пароль");
                    return;
                }
                if (UserPassword.length() < 6) {
                    userLogInPassword.setError("Пароль должен состоять как минимум из 6 символов");
                    return;
                }
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.setLanguageCode("ru");
                auth.signInWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener((Task<AuthResult> task) -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LogInActivity.this, "Вы успешно вошли в аккаунт", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else {
                        Toast.makeText(LogInActivity.this, "Неверный логин или пароль" /*+ task.getException().getMessage()*/, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public boolean isValidEmail(String userEmail) {
        return (PatternsCompat.EMAIL_ADDRESS.matcher(userEmail).matches());
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

    private void btnForgotPassword() {
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login_ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}