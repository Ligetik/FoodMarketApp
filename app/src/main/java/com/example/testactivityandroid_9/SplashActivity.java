package com.example.testactivityandroid_9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.testactivityandroid_9.ui.login.LogInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isConnected()) {
            Intent intent = new Intent(this, MainActivity.class);

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser != null) {
                startActivity(intent);
                finish();
            } else {
                FirebaseAuth.getInstance().signInAnonymously()
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(intent);
                                    finish();

                                }
                            }
                        });
            }
        } else {
            Toast.makeText(SplashActivity.this, "Нет доступа в интернет" , Toast.LENGTH_SHORT).show();
        }


    }

    boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null) {
            if (networkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}