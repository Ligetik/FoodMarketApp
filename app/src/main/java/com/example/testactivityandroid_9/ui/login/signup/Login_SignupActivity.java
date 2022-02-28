package com.example.testactivityandroid_9.ui.login.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.testactivityandroid_9.MainActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.model.BonusModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login_SignupActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    ImageButton imageButton;
    String userID;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
/*    List<BonusModel> BonusModelList;*/

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
    @BindView(R.id.btnAgreement)
    TextView btnAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
/*        this.BonusModelList = BonusModelList;*/
        clickBtnBack();

        ButterKnife.bind(this);

        clickBtnAgreement();

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseAuth.getInstance().setLanguageCode("ru");
/*        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String USER_NUMBER = userNumber.getText().toString().trim();
                final String USER_NAME = userName.getText().toString();
                final String USER_EMAIL = userEmail.getText().toString().trim();;
                final String USER_PASSWORD = userPassword.getText().toString().trim();

                if (TextUtils.isEmpty(USER_NUMBER)) {
                    userNumber.setError("Не указан номер телефона");
                    return;
                }
                if (USER_NUMBER.length() < 10) {
                    userNumber.setError("Неккоректный номер телефона");
                    return;
                }
                if (TextUtils.isEmpty(USER_NAME)) {
                    userName.setError("Не указано Ваше имя");
                    return;
                }
                if (TextUtils.isEmpty(USER_EMAIL)) {
                    userEmail.setError("Не указана электронная почта");
                    return;
                }
                if (!isValidEmail(USER_EMAIL)){
                    userEmail.setError("Неверный формат электронной почты");
                    return;
                }
                if (TextUtils.isEmpty(USER_PASSWORD)) {
                    userPassword.setError("Не указан пароль");
                    return;
                }
                if (USER_PASSWORD.length() < 6) {
                    userPassword.setError("Пароль должен состоять как минимум из 6 символов");
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(USER_EMAIL, USER_PASSWORD).addOnCompleteListener((Task<AuthResult> task) -> {
                    if (task.isSuccessful()) {
                        userID = firebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("user").document(userID);


                        /*List<BonusModel> bonusModels = new ArrayList<>();*/


                        /*BonusModel bonusModel = new BonusModel();*/
                        Map<String, Object>  user = new HashMap<>();
                        user.put("Имя", USER_NAME);
                        user.put("Номер телефона", "+7" + USER_NUMBER);
                        user.put("Пароль", USER_PASSWORD);
                        user.put("Почта", USER_EMAIL);
                        user.put("bonus", 0 /*bonusModel.setBonus(0)*/);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "OnSuccess" + userID);

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(USER_NAME)
                                        .build();
                                firebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                }
                                            }
                                        });
/*                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if(currentUser != null) {
                                    Toast.makeText(Login_SignupActivity.this, "Курент!" , Toast.LENGTH_SHORT).show();
                                }*/
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "OnFailure" + e.toString());
                            }
                        });
                    }else {
                        Toast.makeText(Login_SignupActivity.this, "Этот адрес электронной почты уже используется. Попробуйте другой", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public boolean isValidEmail(String userEmail) {
        return (PatternsCompat.EMAIL_ADDRESS.matcher(userEmail).matches());
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

    private void clickBtnAgreement() {
        btnAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login_Signup_AgreementActivity.class));
            }
        });
    }
}