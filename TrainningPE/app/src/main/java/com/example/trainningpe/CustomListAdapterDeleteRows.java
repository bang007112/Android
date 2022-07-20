package com.example.trainningpe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainningpe.connectDB.EmployeeDatabaseAdapter;
import com.example.trainningpe.connectDB.Employee;

import java.util.ArrayList;

public class CustomListAdapterDeleteRows extends BaseAdapter {
    Context c;
    ArrayList<Employee> employees;

    public CustomListAdapterDeleteRows(Context c, ArrayList<Employee> employees) {
        this.c = c;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listview_employee,viewGroup,false);
        }

        TextView mtextViewID = (TextView) view.findViewById(R.id.textViewID);
        TextView mtextViewFullname = (TextView) view.findViewById(R.id.textViewFullname);
        TextView mtextViewAge = (TextView) view.findViewById(R.id.textViewAge);

        final Employee employee= (Employee) this.getItem(i);

        mtextViewID.setText(employee.getID());
        mtextViewFullname.setText(employee.getFULLNAME());
        mtextViewAge.setText(String.valueOf(employee.getAGE()));

        return view;
    }
}
