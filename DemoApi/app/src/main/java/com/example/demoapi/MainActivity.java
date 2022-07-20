package com.example.demoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.trusted.Token;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Login(View view){
        String url = "https://localhost:5001/api/Login";
        ApiService.apiService.Login("bang007112","123456789").enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Toast.makeText(MainActivity.this,"Call Api Success",Toast.LENGTH_SHORT).show();
                Token token1 = response.body();
                if(token1!=null){
                    Log.v("Token:", token1.toString());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call Api Faile",Toast.LENGTH_SHORT).show();
            }
        });
    }
}