package com.example.pe_dangdohuubang_ce150240;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pe_dangdohuubang_ce150240.connectDB.Subject;

import java.util.ArrayList;

public class SubjectListAdapter extends BaseAdapter {
    Context c;
    ArrayList<Subject> subjects;

    public SubjectListAdapter(Context c, ArrayList<Subject> subjects) {
        this.c = c;
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int i) {
        return subjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listview_subject,viewGroup,false);
        }

        TextView mtextViewID_sub = (TextView) view.findViewById(R.id.textViewID_sub);
        TextView mtextViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        TextView mtextViewNocredit = (TextView) view.findViewById(R.id.textViewNocredit);

        final Subject subject= (Subject) this.getItem(i);

        mtextViewID_sub.setText(subject.getID_SUB());
        mtextViewTitle.setText(subject.getTITLE_SUB());
        mtextViewNocredit.setText(String.valueOf(subject.getNOCREDIT()));

        return view;
    }

}
