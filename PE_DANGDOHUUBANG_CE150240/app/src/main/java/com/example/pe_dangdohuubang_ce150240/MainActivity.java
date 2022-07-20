package com.example.pe_dangdohuubang_ce150240;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pe_dangdohuubang_ce150240.connectDB.Subject;
import com.example.pe_dangdohuubang_ce150240.connectDB.SubjectDatabaseAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SubjectDatabaseAdapter subjectDatabaseAdapter;
    ArrayList<Subject> subjects = new ArrayList<>();
    ListView listView ;
    static SubjectListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subjectDatabaseAdapter = new SubjectDatabaseAdapter(getApplicationContext());
        //Update data
            //subjectDatabaseAdapter.insertEntry("PRU211","C# Programming and Unity",3);
            //subjectDatabaseAdapter.insertEntry("OSG202","Operating system",3);
            //subjectDatabaseAdapter.insertEntry("PRJ301","Java Web Application Development",3);
        try {
            subjects = SubjectDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter = new SubjectListAdapter(this, subjects);
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
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        dialog.getWindow().setLayout(width, height);

        TextView tvID = dialog.findViewById(R.id.textViewID);
        TextView tvTitle = dialog.findViewById(R.id.textViewtitle);
        TextView tvNocredit = dialog.findViewById(R.id.textViewnocredit);

        Button btn_update = dialog.findViewById(R.id.btn_update);
        Button btn_close = dialog.findViewById(R.id.btn_close);

        Subject subject = (Subject) listView.getItemAtPosition(position);

        if(subject!=null) {
            tvID.setText(subject.getID_SUB());
            tvTitle.setText(subject.getTITLE_SUB());
            tvNocredit.setText(String.valueOf(subject.getNOCREDIT()));
        }
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    public void Filter(){
        EditText edMin = findViewById(R.id.editTextMin);
        EditText edMax = findViewById(R.id.editTextMax);
        try {
            subjects = SubjectDatabaseAdapter.getRows();
            for (int i = 0; i < subjects.size(); i++) {
                if(subjects.get(i).getNOCREDIT() > Integer.parseInt(edMin.getText().toString() )&& subjects.get(i).getNOCREDIT()<Integer.parseInt(edMax.getText().toString())){

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}