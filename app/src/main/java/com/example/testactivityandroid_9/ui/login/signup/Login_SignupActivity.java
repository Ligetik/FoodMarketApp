package com.example.testactivityandroid_9.ui.login.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.testactivityandroid_9.MainActivity;
import com.example.testactivityandroid_9.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login_SignupActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    ImageButton imageButton;
    String userID;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;


    @BindView(R.id.userNumber)
    EditText userNumber;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userEmail)
    EditText userEmail;
    @BindView(R.id.userPassword)
    EditText userPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        clickBtnBack();

        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

/*        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String UserNumber = userNumber.getText().toString().trim();
                final String UserName = userName.getText().toString();
                final String UserEmail = userEmail.getText().toString();
                String UserPassword = userPassword.getText().toString().trim();

                if (TextUtils.isEmpty(UserNumber)) {
                    userNumber.setError("Не указан номер телефона");
                    return;
                }
                if (UserNumber.length() < 10) {
                    userNumber.setError("Неккоректный номер телефона");
                    return;
                }
                if (TextUtils.isEmpty(UserEmail)) {
                    userEmail.setError("Не указан электронный адрес");
                    return;
                }
                if (TextUtils.isEmpty(UserPassword)) {
                    userPassword.setError("Не указан пароль");
                    return;
                }
                if (UserPassword.length() < 6) {
                    userPassword.setError("Пароль должен состоять как минимум из 6 символов");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener((Task<AuthResult> task) -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login_SignupActivity.this, "User created", Toast.LENGTH_SHORT).show();
                        userID = firebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("user").document(userID);

                        Map<String, Object>  user = new HashMap<>();
                        user.put("Имя", UserName);
                        user.put("Номер телефона", "+7" + UserNumber);
                        user.put("Пароль", UserPassword);
                        user.put("Почта", UserEmail);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "OnSuccess" + userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "OnFailure" + e.toString());
                            }
                        });
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else {
                        Toast.makeText(Login_SignupActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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