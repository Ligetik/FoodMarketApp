package com.example.testactivityandroid_9;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.model.BonusModel;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.ui.login.LogInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile extends AppCompatActivity {

    ImageButton imageButton;
    @BindView(R.id.profileName)
    TextView profileName;
    @BindView(R.id.profileBonus)
    TextView profileBonus;
    @BindView(R.id.btnLogOut)
    Button btnLogOut;
    BonusModel bonusModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        clickBtnBack();

        clickBtnLogOut();

        profileName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        FirebaseFirestore.getInstance()
                .collection("user")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        int bonus = documentSnapshot.getLong("bonus").intValue();
                        profileBonus.setText(bonus + "");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

                        /*new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                BonusModel bonusModel = documentSnapshot.toObject(BonusModel.class);

                              *//*  cartModel.setKey(documentSnapshot.getId());*//*

                                bonusModelList.add(bonusModel);

                            }
                        }
                    }
                });*/


  /*  private CharSequence extracted() {
        FirebaseFirestore.getInstance()
                .collection("user")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("bonus")
                .whereEqualTo("", true);
        return null;*/
    /*}*/

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void clickBtnLogOut() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(Profile.this)
                        .setTitle("Выйти из профиля")
                        .setMessage("Вы уверены, что хотите выйти?")
                        .setNegativeButton("Нет", (dialog1, which) -> dialog1.dismiss())
                        .setPositiveButton("Да", (dialog12, which) -> {

                            FirebaseAuth.getInstance().signOut();

                            FirebaseAuth.getInstance().signInAnonymously()
                                    .addOnCompleteListener(Profile.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }
                                        }
                                    });

                            dialog12.dismiss();

                        }).create();
                dialog.show();
            }
        });
    }
}