package com.example.trainningpe.connectDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trainningpe.connectDB.EmployeeDatabaseAdapter;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(EmployeeDatabaseAdapter.DATABASE_CREATE);
        }catch(Exception er){
            Log.e("Error","exception");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        _db.execSQL("DROP TABLE IF EXISTS " + "EMPLOYEE");
        _db.execSQL("DROP TABLE IF EXISTS " + "SEMESTER1");
        // Create a new one.
        onCreate(_db);
    }
}
