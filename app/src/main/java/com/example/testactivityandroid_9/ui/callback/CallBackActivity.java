package com.example.testactivityandroid_9.ui.callback;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.testactivityandroid_9.R;

public class CallBackActivity extends AppCompatActivity {
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback);

        clickBtnBack();
    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed();
            }
        });
    }
}