package com.example.preparepe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.preparepe.connectDB.Customer;

import java.util.ArrayList;

public class CustomerListAdapter extends BaseAdapter {
    Context c;
    ArrayList<Customer> customers;

    public CustomerListAdapter(Context c, ArrayList<Customer> customers) {
        this.c = c;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int i) {
        return customers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listview_customer,viewGroup,false);
        }

        TextView mtextViewID = (TextView) view.findViewById(R.id.textViewID);
        TextView mtextViewFullname = (TextView) view.findViewById(R.id.textViewFullname);
        TextView mtextViewAge = (TextView) view.findViewById(R.id.textViewAge);
        TextView mtextViewBirthday = (TextView) view.findViewById(R.id.textViewBirthday);
        TextView mtextViewAddress = (TextView) view.findViewById(R.id.textViewAddress);

        final Customer customer= (Customer) this.getItem(i);

        mtextViewID.setText(customer.getID());
        mtextViewFullname.setText(customer.getFULLNAME());
        mtextViewAge.setText(String.valueOf(customer.getAGE()));
        mtextViewBirthday.setText(customer.getDATE_OF_BIRTH());
        mtextViewAddress.setText(customer.getADDRESS());

        return view;
    }
}
