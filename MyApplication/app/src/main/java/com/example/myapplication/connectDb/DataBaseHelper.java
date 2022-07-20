package com.example.myapplication.connectDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.myapplication.connectDb.UsersDatabaseAdapter;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(UsersDatabaseAdapter.DATABASE_CREATE);
            _db.execSQL(AppointmentDatabaseAdapter.DATABASE_CREATE);
            _db.execSQL(FeedbackDatabaseAdapter.DATABASE_CREATE);
        }catch(Exception er){
            Log.e("Error","exceptioin");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        _db.execSQL("DROP TABLE IF EXISTS " + "USERS");
        _db.execSQL("DROP TABLE IF EXISTS " + "APPOINTMENTS");
        _db.execSQL("DROP TABLE IF EXISTS " + "FEEDBACKS");
        _db.execSQL("DROP TABLE IF EXISTS " + "SEMESTER1");
        // Create a new one.
        onCreate(_db);
    }
}
