package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.connectDb.AppointmentDatabaseAdapter;
import com.example.myapplication.connectDb.FeedbackDatabaseAdapter;
import com.example.myapplication.connectDb.UsersDatabaseAdapter;

import java.time.LocalDateTime;
import java.util.Date;

public class insertRowActivity extends AppCompatActivity {

    private TextView mUserName;
    private TextView mUserPhone;
    private TextView mUserEmail;
    private Button insertRowFrom;
    private TextView mUserPassword;
    private TextView mUserAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        insertRowFrom = (Button) findViewById(R.id.insertRowFrom);
        mUserName = (TextView) findViewById(R.id.userNameTxt);
        mUserPhone = (TextView) findViewById(R.id.userPhoneTxt);
        mUserEmail = (TextView) findViewById(R.id.userEmailTxt);
        mUserPassword = (TextView) findViewById(R.id.userPasswordTxt);
        mUserAddress = (TextView) findViewById(R.id.userAddressTxt);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Inser New Row in SQLite");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertRow(View view) {

        TextView userNameTxtView = findViewById(R.id.userNameTxt);
        TextView userPhoneTxtView = findViewById(R.id.userPhoneTxt);
        TextView userEmailTxtView = findViewById(R.id.userEmailTxt);
        TextView userPasswordTxtView = findViewById(R.id.userPasswordTxt);
        TextView userAddressTxtView = findViewById(R.id.userAddressTxt);



        if(userNameTxtView.getText().toString().trim().equals("")
                || userPhoneTxtView.getText().toString().trim().equals("")
                || userEmailTxtView.getText().toString().trim().equals("")){
        }else{
            UsersDatabaseAdapter.insertEntry(userNameTxtView.getText().toString().trim(),userPhoneTxtView.getText().toString(),userEmailTxtView.getText().toString(), userPasswordTxtView.getText().toString(), userAddressTxtView.getText().toString());
            FeedbackDatabaseAdapter.insertEntry("new feedback", LocalDateTime.of(2020,10,10,6,6),1);
            Intent myIntent = new Intent(insertRowActivity.this, MainActivity.class);
            insertRowActivity.this.startActivity(myIntent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}