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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

        btnCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String UserName = callbackName.getText().toString();
                final String UserNumber = callbackNumber.getText().toString().trim();

                if (TextUtils.isEmpty(UserName)) {
                    callbackName.setError("Не указано Ваше имя");
                    return;
                }
                if (TextUtils.isEmpty(UserNumber)) {
                    callbackNumber.setError("Не указан номер телефона");
                    return;
                }
                if (UserNumber.length() < 10) {
                    callbackNumber.setError("Неккоректный номер телефона");
                    return;
                }

                Map<String, Object> user = new HashMap<>();
                user.put("Имя", UserName);
                user.put("Номер телефона", "+7" + UserNumber);

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Данные обратной связи")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "Заявка отправлена!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String error = e.getMessage();
                                Toast.makeText(getApplicationContext(), "Ошибка: " + error,Toast.LENGTH_SHORT).show();
                            }
                        });
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed(); }
        });
    }

}