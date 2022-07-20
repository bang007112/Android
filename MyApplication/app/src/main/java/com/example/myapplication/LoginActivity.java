package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.connectDb.UsersDatabaseAdapter;
import com.example.myapplication.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Login(View view){
        TextView mUserName = (TextView) findViewById(R.id.txtUsername);
        TextView mUserPassword = (TextView) findViewById(R.id.txtPassword);
        if(mUserName.getText().toString().trim().equals("") || mUserPassword.getText().toString().trim().equals("")){

        }else {
           UserModel user = UsersDatabaseAdapter.Login(mUserName.getText().toString(),mUserPassword.getText().toString());
           if (user != null){
               Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
               LoginActivity.this.startActivity(myIntent);
               Toast.makeText(getApplicationContext(),"Login Success", Toast.LENGTH_LONG );
           }else {
               Toast.makeText(getApplicationContext(),"Login Fail", Toast.LENGTH_LONG );

           }
        }
    }
}