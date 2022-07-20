package com.example.facebook_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private static final String FILENAME = "login";
    private static final String USERNAME = "username";
    SharedPreferences sharedPreferences;
    TextView tvMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvMessage = findViewById(R.id.txtHeader);

        sharedPreferences = getSharedPreferences(FILENAME, MODE_PRIVATE);
        if (sharedPreferences.contains(USERNAME)) {
            tvMessage.setText("Hello" + sharedPreferences.getString(USERNAME, ""));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences.Editor editor = getSharedPreferences(FILENAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }
}