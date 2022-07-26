package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.connectDb.UsersDatabaseAdapter;
import com.example.myapplication.models.UserModel;

import org.json.JSONException;

import java.util.ArrayList;

public class UpdateRowsActivity extends AppCompatActivity {

    static ListView listView ;
    ArrayList<UserModel> users=new ArrayList<>();
    static CustomListAdapterUpdateRows updateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rows);
        try {
            users = UsersDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        updateAdapter = new CustomListAdapterUpdateRows(this, users);
        listView = (ListView) findViewById(R.id.listupdateviewID);
        listView.setAdapter((ListAdapter) updateAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Update Row in SQLite");
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