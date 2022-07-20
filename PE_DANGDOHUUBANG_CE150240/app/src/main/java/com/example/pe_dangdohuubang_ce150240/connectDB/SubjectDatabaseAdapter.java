package com.example.pe_dangdohuubang_ce150240.connectDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

public class SubjectDatabaseAdapter {
    static ArrayList<Subject> subjects=new ArrayList<>();
    static final String DATABASE_NAME = "SubjectDatabase.db";
    static final String TABLE_NAME = "SUBJECT";
    static final int DATABASE_VERSION = 1;
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+TABLE_NAME+"( ID_SUB text primary key,TITLE_SUB text, NOCREDIT integer);";
    private static final String TAG = "SubjectDatabaseAdapter:";


    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;
    public SubjectDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public SubjectDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table
    public static String insertEntry(String ID_sub,String Title_sub, int Nocredit)
    {

        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("ID_SUB", ID_sub);
            newValues.put("TITLE_SUB", Title_sub);
            newValues.put("NOCREDIT", Nocredit);

            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            db.close();

        }catch(Exception ex) {

        }
        return "ok";
    }
    public static Subject getEmployee(String ID) throws JSONException {
        Subject subject;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {
            subject=new Subject();
            subject.setID_SUB(projCursor.getString(projCursor.getColumnIndexOrThrow("ID")));
            if(subject.getID_SUB().equals(ID)){
                subject.setTITLE_SUB(projCursor.getString(projCursor.getColumnIndexOrThrow("fullname")));
                subject.setNOCREDIT(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("age"))));
                return subject;
            }

        }
        projCursor.close();
        return null;
    }

    // method to get all Rows Saved in Table
    public static ArrayList<Subject> getRows() throws JSONException {

        subjects.clear();
        Subject subject;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {
            subject =new Subject();
            subject.setID_SUB(projCursor.getString(projCursor.getColumnIndexOrThrow("ID_SUB")));
            subject.setTITLE_SUB(projCursor.getString(projCursor.getColumnIndexOrThrow("TITLE_SUB")));
            subject.setNOCREDIT(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("NOCREDIT"))));
            subjects.add(subject);
        }
        projCursor.close();
        return subjects;
    }

    // method to delete a Record in Tbale using Primary Key Here it is ID
    public static int deleteEntry(String ID)
    {
        String where="ID=?";
        int numberOFEntriesDeleted= db.delete(TABLE_NAME, where, new String[]{ID}) ;
        return numberOFEntriesDeleted;
    }

    // method to get Count of Toatal Rows in Table
    public static int getRowCount()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
        db.close();
        return cursor.getCount();
    }

    // method to Truncate/ Remove All Rows in Table
    public static void truncateTable()
    {
        db=dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "1", null);
        db.close();
    }

    // method to Update an Existing Row in Table
    public static void  updateEntry(String ID,String Title_sub, int Nocredit)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("ID_SUB",ID);
        updatedValues.put("TITLE_SUB", Title_sub);
        updatedValues.put("NOCREDIT", String.valueOf(Nocredit));

        String where="ID = ?";
        db=dbHelper.getReadableDatabase();
        db.update(TABLE_NAME,updatedValues, where, new String[]{ID});
        db.close();
    }
}
