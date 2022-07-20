package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.connectDb.AppointmentDatabaseAdapter;
import com.example.myapplication.connectDb.FeedbackDatabaseAdapter;
import com.example.myapplication.connectDb.UsersDatabaseAdapter;

public class MainActivity extends AppCompatActivity {
    UsersDatabaseAdapter usersDatabaseAdapter;
    AppointmentDatabaseAdapter appointmentDatabaseAdapter;
    FeedbackDatabaseAdapter feedbackDatabaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appointmentDatabaseAdapter = new AppointmentDatabaseAdapter(getApplicationContext());
        usersDatabaseAdapter =new UsersDatabaseAdapter(getApplicationContext());
        feedbackDatabaseAdapter = new FeedbackDatabaseAdapter(getApplicationContext());
    }

    public void insertRowActivity(View view) {
        Intent myIntent = new Intent(MainActivity.this, insertRowActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    //Open activity to update rows
    public void updateRowView(View view) {
        Intent myIntent = new Intent(MainActivity.this, UpdateRowsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    //Open activity to delete rows
    public void deleteRowActivity(View view) {
        Intent myIntent = new Intent(MainActivity.this, deleteRowsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void Login(View view){
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void LoginFacebook(View view){
        Intent myIntent = new Intent(MainActivity.this, LoginFacebook.class);
        MainActivity.this.startActivity(myIntent);
    }

    //Button method to truncate table rows
    public void truncateTable(View view) {
        UsersDatabaseAdapter.truncateTable();
    }

    //Open URL in browser
    public void goToUrl (View view) {
        String url = "http://www.google.com";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}