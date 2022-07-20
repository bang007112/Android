package com.example.trainningpe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.trainningpe.connectDB.Employee;
import com.example.trainningpe.connectDB.EmployeeDatabaseAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EmployeeDatabaseAdapter employeeDatabaseAdapter;

    ListView listView ;
    ArrayList<Employee> employees = new ArrayList<>();
    static CustomListAdapterDeleteRows deleteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        employeeDatabaseAdapter = new EmployeeDatabaseAdapter(getApplicationContext());
        super.onCreate(savedInstanceState);
        EmployeeDatabaseAdapter.insertEntry("BA","Bang",21);
        setContentView(R.layout.activity_main);
        try {
            employees = EmployeeDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        deleteAdapter = new CustomListAdapterDeleteRows(this, employees);
        listView = (ListView) findViewById(R.id.listdeleteviewID);
        listView.setAdapter(deleteAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Delete Row from SQLite");
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
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void openDialog(int position) throws JSONException {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogview);

        TextView edID =  dialog.findViewById(R.id.textViewID);
        EditText edFullname =  dialog.findViewById(R.id.textViewFullname);
        EditText edAge = dialog.findViewById(R.id.textViewAge);

        Button btn_delete = dialog.findViewById(R.id.btn_delete);
        Button btn_update = dialog.findViewById(R.id.btn_update);


        Employee employee = (Employee)listView.getItemAtPosition(position);

       if(employee!=null){
            edID.setText(employee.getID());
            edFullname.setText(employee.getFULLNAME());
            edAge.setText(String.valueOf(employee.getAGE()));
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeDatabaseAdapter.deleteEntry(employee.getID());
                    employees.remove(employee);
                    finish();
                    startActivity(getIntent());
                }
            });
           btn_update.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   EmployeeDatabaseAdapter.updateEntry(edID.getText().toString().trim(),edFullname.getText().toString().trim(),Integer.parseInt(edAge.getText().toString().trim()));
                   finish();
                   startActivity(getIntent());
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

