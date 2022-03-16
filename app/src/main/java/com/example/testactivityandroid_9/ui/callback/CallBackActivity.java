package com.example.testactivityandroid_9.ui.callback;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.MainActivity;
import com.example.testactivityandroid_9.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.common.base.Joiner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

    NavigationView navigationView;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);

        ButterKnife.bind(this);

        clickBtnBack();

        btnCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = callbackName.getText().toString();
                final String userNumber = callbackNumber.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    callbackName.setError("Не указано Ваше имя");
                    return;
                }
                if (TextUtils.isEmpty(userNumber)) {
                    callbackNumber.setError("Не указан номер телефона");
                    return;
                }
                if (userNumber.length() < 10) {
                    callbackNumber.setError("Неккоректный номер телефона");
                    return;
                }


                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd в HH:mm");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                Map<String, Object> userContact = new HashMap<>();
                userContact.put("Имя:", userName);
                userContact.put("Номер телефона:", "+7" + userNumber);
                userContact.put("Дата и время:",  strDate);

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Данные обратной связи")
                        .add(userContact)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                String orderContactInfoMapStrPizza = Joiner.on("<br>").withKeyValueSeparator(" ").join(userContact);

                                Map<String,Object> message = new HashMap<>();
                                message.put("subject", "Поступили данные для обратной связи");
                                message.put("html", orderContactInfoMapStrPizza);

                                Map<String,Object> mail = new HashMap<>();
                                mail.put("to", "dasha.gubar.23@inbox.ru");
                                mail.put("message", message);

                                FirebaseFirestore.getInstance()
                                        .collection("mail")
                                        .add(mail);

                                Toast.makeText(getApplicationContext(), "Заявка отправлена!",Toast.LENGTH_SHORT).show();


                                /*Resources res = getResources();
                                String text2 = "ddddd"*/;
                                /*String text3 = getString("");*/
                                /*String.format(res.getString(R.string.username), text2);*/
                                /*getString(R.string.username, text2);*/

                                /*navigationView = (NavigationView) findViewById(R.id.nav_view);*/
                             /*   Menu nav_Menu = navigationView.getMenu();
                                nav_Menu.findItem(R.id.nav_profile).setVisible(false);*/




/*                                Menu menu = navigationView.getMenu();

                                *//*menu.findItem(R.id.nav_profile).setTitle("My Account");
                                menu.findItem(R.id.nav_profile).setVisible(false);*//*


                                menu.findItem(R.id.nav_profile).setTitle("My Account");*/

                                /*MainActivity activity=(MainActivity)mainActivity;*/


//                                MainActivity mActivity= new MainActivity();
                                /*mActivity.extracted(navigationView);*/
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