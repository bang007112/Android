package com.example.trainningpe.connectDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trainningpe.connectDB.Employee;

import org.json.JSONException;

import java.util.ArrayList;

public class EmployeeDatabaseAdapter {
    static ArrayList<Employee> employees=new ArrayList<>();
    static final String DATABASE_NAME = "EmployeeDatabase.db";
    static final String TABLE_NAME = "EMPLOYEE";
    static final int DATABASE_VERSION = 1;
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+TABLE_NAME+"( ID text primary key,fullname text, age integer);";
    private static final String TAG = "EmployeeDatabaseAdapter:";


    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;
    public EmployeeDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public EmployeeDatabaseAdapter open() throws SQLException
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
    public static String insertEntry(String ID,String fullname, int age)
    {

        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("ID", ID);
            newValues.put("fullname", fullname);
            newValues.put("age", age);

            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }
    public static Employee getEmployee(String ID) throws JSONException {
        Employee employee;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {
            employee=new Employee();
            employee.setID(projCursor.getString(projCursor.getColumnIndexOrThrow("ID")));
            if(employee.getID().equals(ID)){
                employee.setFULLNAME(projCursor.getString(projCursor.getColumnIndexOrThrow("fullname")));
                employee.setAGE(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("age"))));
                return employee;
            }

        }
        projCursor.close();
        return null;
    }

    // method to get all Rows Saved in Table
    public static ArrayList<Employee> getRows() throws JSONException {

        employees.clear();
        Employee employee;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {
            employee=new Employee();

            employee.setID(projCursor.getString(projCursor.getColumnIndexOrThrow("ID")));
            employee.setFULLNAME(projCursor.getString(projCursor.getColumnIndexOrThrow("fullname")));
            employee.setAGE(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("age"))));
            employees.add(employee);
        }
        projCursor.close();
        return employees;
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
    public static void  updateEntry(String ID,String fullname, int age)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("fullname", fullname);
        updatedValues.put("age", age);

        String where="ID = ?";
        db=dbHelper.getReadableDatabase();
        db.update(TABLE_NAME,updatedValues, where, new String[]{ID});
        db.close();
    }
}