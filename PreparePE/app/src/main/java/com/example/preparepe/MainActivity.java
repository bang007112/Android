package com.example.preparepe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preparepe.connectDB.Customer;
import com.example.preparepe.connectDB.CustomerDatabaseAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomerDatabaseAdapter customerDatabaseAdapter;

    ListView listView ;
    ArrayList<Customer> customers = new ArrayList<>();
    static CustomerListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerDatabaseAdapter = new CustomerDatabaseAdapter(getApplicationContext());

        try {
            customers = CustomerDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter = new CustomerListAdapter(this, customers);
        listView = (ListView) findViewById(R.id.listviewID);
        listView.setAdapter(listAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Customer Management SQLite");
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    openDialog(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    return true;
            }
        });
    }
    public void openDialog(int position) throws JSONException {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogview);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
        dialog.getWindow().setLayout(width,height);

        TextView edID =  dialog.findViewById(R.id.textViewID);
        EditText edFullname =  dialog.findViewById(R.id.edittextFullname);
        EditText edAge = dialog.findViewById(R.id.edittextAge);
        EditText edBirthday = dialog.findViewById(R.id.edittextBirthday);
        EditText edAddress = dialog.findViewById(R.id.edittextAddress);


        Button btn_delete = dialog.findViewById(R.id.btn_delete);
        Button btn_update = dialog.findViewById(R.id.btn_update);

        Button btn_close = dialog.findViewById(R.id.btn_close);


        Customer customer = (Customer) listView.getItemAtPosition(position);

        if(customer!=null){
            edID.setText(customer.getID());
            edFullname.setText(customer.getFULLNAME());
            edAge.setText(String.valueOf(customer.getAGE()));
            edBirthday.setText(customer.getDATE_OF_BIRTH());
            edAddress.setText(customer.getADDRESS());
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomerDatabaseAdapter.deleteEntry(customer.getID());
                    customers.remove(customer);
                    finish();
                    startActivity(getIntent());
                }
            });
            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomerDatabaseAdapter.updateEntry(edID.getText().toString().trim(),edFullname.getText().toString().trim(),Integer.parseInt(edAge.getText().toString().trim()),edBirthday.getText().toString(),edAddress.getText().toString());
                    finish();
                    startActivity(getIntent());
                }
            });
            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        }
        dialog.show();
    }
    public void insert_employee(View view){
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);
        startActivity(intent);
    }

}