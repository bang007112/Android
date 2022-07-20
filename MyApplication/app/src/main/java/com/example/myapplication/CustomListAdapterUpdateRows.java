package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.connectDb.UsersDatabaseAdapter;
import com.example.myapplication.models.UserModel;

import java.util.ArrayList;

public class CustomListAdapterUpdateRows extends BaseAdapter {
    Context c;
    ArrayList<UserModel> users;

    public CustomListAdapterUpdateRows(Context c, ArrayList<UserModel> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listviewupdate_row,viewGroup,false);
        }
        final EditText meditText0= (EditText) view.findViewById(R.id.editText0);

        final EditText meditText1 = (EditText) view.findViewById(R.id.editText1);
        final EditText meditText2 = (EditText) view.findViewById(R.id.editText2);
        final EditText meditText3 = (EditText) view.findViewById(R.id.editText3);
        final EditText meditText4 = (EditText) view.findViewById(R.id.editText4);
        final EditText meditText5 = (EditText) view.findViewById(R.id.editText5);
        Button updateBtn = (Button) view.findViewById(R.id.updateBtn);

        final UserModel user= (UserModel) this.getItem(i);
        meditText0.setText(user.getID());
        meditText1.setText(user.getUsername());
        meditText2.setText(user.getUserphone());
        meditText3.setText(user.getUseremail());
        meditText4.setText(user.getPassword());

        meditText5.setText(user.getAddress());




        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String col1value = meditText1.getText().toString();
                String col2value = meditText2.getText().toString();
                String col3value = meditText3.getText().toString();
                String col4value = meditText4.getText().toString();
                String col5value = meditText5.getText().toString();

                UsersDatabaseAdapter.updateEntry(user.getID(),col1value,col2value,col3value,col4value,col5value);
            }
        });

        return view;
    }
}
