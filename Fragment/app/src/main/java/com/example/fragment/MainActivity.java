package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnSend;
// su dung bien trung gian
//    private String mEmail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edt_email);
        btnSend = findViewById(R.id.btn_send);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToFragment();
            }
        });
    }

    private void sendDataToFragment() {
        String strEmail = edtEmail.getText().toString().trim();

        //mEmail = strEmail;
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key email", strEmail);
        homeFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, homeFragment);
        fragmentTransaction.commit();
    }

//    public String getEmail() {
//        return mEmail;
//    }

    public EditText getEdtEmail() {
        return edtEmail;
    }
}