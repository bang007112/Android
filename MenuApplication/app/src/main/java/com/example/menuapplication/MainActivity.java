package com.example.menuapplication;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 0;

    Button btnFunc;
    ConstraintLayout manHinh;
    private  static final  int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        manHinh = (ConstraintLayout) findViewById(R.id.manHinh);

        // Đăng kí context menu
        registerForContextMenu(btnFunc);

        Button btnPermiss = findViewById(R.id.permiss);
        Button btnDeny = findViewById(R.id.Deny);
        Button btnSetting = findViewById(R.id.btnSetting);
        btnPermiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOpenPermission();
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        Button btnNotification = findViewById(R.id.btn_Notifi);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifications();
            }
        });
    }
    private void sendNotifications() {


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(MainActivity.this, Channel.CHANNEL_ID)
                .setContentTitle("Tile Notification")
                .setContentText("Message Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(bitmap)
                //x.setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if(notificationManager != null){
            notificationManager.notify(NOTIFICATION_ID, notification);
            Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
        }
    }


    private void clickOpenPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R){
            return;
        }
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ){
            Toast.makeText(this, "Permission Grandted", Toast.LENGTH_SHORT).show();
        }else {
            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permission, REQUEST_PERMISSION_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Permission Was Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Select option");
        menu.setHeaderIcon(R.mipmap.ic_launcher);

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuSearch:
                Toast.makeText(this, "Chuc nang Search da duoc chon", Toast.LENGTH_SHORT).show();
                manHinh.setBackgroundColor(Color.CYAN);
                return true;
            case R.id.menuCart:
                Toast.makeText(this, "Chuc nang Cart da duoc chon", Toast.LENGTH_SHORT).show();
                manHinh.setBackgroundColor(Color.MAGENTA);
                return true;

        }
        return super.onContextItemSelected(item);
    }
}