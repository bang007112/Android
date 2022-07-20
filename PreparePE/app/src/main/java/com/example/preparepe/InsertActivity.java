package com.example.preparepe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preparepe.connectDB.CustomerDatabaseAdapter;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }
    public void insert_employee(View view){
        EditText edId = findViewById(R.id.editTextID);
        EditText edFullname = findViewById(R.id.editTextFullname);
        EditText edAge = findViewById(R.id.editTextAge);
        EditText edBirthday = findViewById(R.id.editTextBirthday    );
        EditText edAddress = findViewById(R.id.edittextAddress);

        if(edId.getText()!=null && edFullname.getText()!=null && edAge.getText()!=null){
            CustomerDatabaseAdapter.insertEntry(edId.getText().toString().trim(),edFullname.getText().toString().trim(),Integer.parseInt(edAge.getText().toString().trim()),edBirthday.getText().toString(),edAddress.getText().toString());
            Toast.makeText(getApplicationContext(),"Insert Success",Toast.LENGTH_LONG);
            Intent intent = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Insert fail",Toast.LENGTH_LONG);
        }
    }
}