package com.example.testactivityandroid_9.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.testactivityandroid_9.MainActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.listener.CheckLogInListener;
import com.example.testactivityandroid_9.ui.login.signup.Login_SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    /*CheckLogInListener checkLogInListener = new MainActivity();*/
    ImageButton imageButton;
    Button regButton;
    private FirebaseAuth mAuth;

/*    final String checkLogIn = "true";*/

    @BindView(R.id.btnLogIn)
    Button btnLogIn;
    @BindView(R.id.userLogInNumber)
    EditText userLogInNumber;
    @BindView(R.id.userLogInEmail)
    EditText userLogInEmail;
    @BindView(R.id.userLogInPassword)
    EditText userLogInPassword;
    /*private boolean a;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clickBtnBack();

        clickBtnRegistration();

        ButterKnife.bind(this);

        clickBtnLogIn();
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
                final String UserEmail = userLogInEmail.getText().toString();
                String UserPassword = userLogInPassword.getText().toString().trim();

/*                AuthCredential credential = EmailAuthProvider.getCredential(UserEmail, UserPassword);

                mAuth.getCurrentUser().linkWithCredential(credential)
                        .addOnCompleteListener((Task<AuthResult> task) -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(LogInActivity.this, "Link ", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LogInActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });*/

                FirebaseAuth.getInstance().signInWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener((Task<AuthResult> task) -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LogInActivity.this, "Вы успешно вошли в аккаунт", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        /*transferCheckLogIn();*/

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else {
                        Toast.makeText(LogInActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
/*    public void transferCheckLogIn() {
        checkLogInListener.onReceive(checkLogIn);
    }*/

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