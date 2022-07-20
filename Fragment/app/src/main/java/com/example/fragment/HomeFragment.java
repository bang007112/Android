package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class HomeFragment extends Fragment {


    private EditText edtEmail;
    private Button btnUpdate;

    private View mView;
    private MainActivity mMainActivity;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        mMainActivity = (MainActivity) getActivity();
        initUI();
        return mView;
    }

    private void initUI() {
        edtEmail = mView.findViewById(R.id.edt_email);
        btnUpdate = mView.findViewById(R.id.btn_update);

        //edtEmail.setText(mMainActivity.getEmail());
        edtEmail.setText(getArguments().getString("key email"));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToActivity();
            }
        });
    }

    private void sendDataToActivity() {
        String strEmailUpdate = edtEmail.getText().toString().trim();
        mMainActivity.getEdtEmail().setText(strEmailUpdate);
    }
}