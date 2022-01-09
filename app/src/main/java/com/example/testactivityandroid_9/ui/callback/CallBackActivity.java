package com.example.testactivityandroid_9.ui.callback;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.MainActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.ui.login.signup.Login_SignupActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallBackActivity extends AppCompatActivity {
    ImageButton imageButton;
    @BindView(R.id.callbackName)
    EditText callbackName;
    @BindView(R.id.callbackNumber)
    EditText callbackNumber;
    @BindView(R.id.btnCallback)
    Button btnCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);

        ButterKnife.bind(this);

        clickBtnBack();

       /* btnCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String UserNumber = callbackName.getText().toString().trim();
                final String UserName = callbackNumber.getText().toString();

                if (TextUtils.isEmpty(UserNumber)) {
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

                        Map<String, Object> user = new HashMap<>();
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
        });*/
    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed(); }
        });
    }

}