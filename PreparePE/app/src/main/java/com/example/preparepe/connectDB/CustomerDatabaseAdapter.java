package com.example.preparepe.connectDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

public class CustomerDatabaseAdapter {
    static ArrayList<Customer> customers=new ArrayList<>();
    static final String DATABASE_NAME = "CustomerDatabase.db";
    static final String TABLE_NAME = "CUSTOMER";
    static final int DATABASE_VERSION = 1;
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+TABLE_NAME+"( ID text primary key,fullname text, age integer, date_of_birth date, address text);";
    private static final String TAG = "CustomerDatabaseAdapter:";


    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;
    public CustomerDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public CustomerDatabaseAdapter open() throws SQLException
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
    public static String insertEntry(String ID,String fullname, int age, String date_of_birth, String address)
    {

        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("ID", ID);
            newValues.put("fullname", fullname);
            newValues.put("age", age);
            newValues.put("date_of_birth", date_of_birth);
            newValues.put("address", address);

            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            db.close();

        }catch(Exception ex) {

        }
        return "ok";
    }
    public static Customer getEmployee(String ID) throws JSONException {
        Customer customer;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {
            customer=new Customer();
            customer.setID(projCursor.getString(projCursor.getColumnIndexOrThrow("ID")));
            if(customer.getID().equals(ID)){
                customer.setFULLNAME(projCursor.getString(projCursor.getColumnIndexOrThrow("fullname")));
                customer.setAGE(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("age"))));
                customer.setDATE_OF_BIRTH(projCursor.getString(projCursor.getColumnIndexOrThrow("date_of_birth")));
                customer.setADDRESS(projCursor.getString(projCursor.getColumnIndexOrThrow("address")));
                return customer;
            }

        }
        projCursor.close();
        return null;
    }

    // method to get all Rows Saved in Table
    public static ArrayList<Customer> getRows() throws JSONException {

        customers.clear();
        Customer customer;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {
            customer=new Customer();

            customer.setID(projCursor.getString(projCursor.getColumnIndexOrThrow("ID")));
            customer.setFULLNAME(projCursor.getString(projCursor.getColumnIndexOrThrow("fullname")));
            customer.setAGE(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("age"))));
            customer.setDATE_OF_BIRTH(projCursor.getString(projCursor.getColumnIndexOrThrow("date_of_birth")));
            customer.setADDRESS(projCursor.getString(projCursor.getColumnIndexOrThrow("address")));
            customers.add(customer);
        }
        projCursor.close();
        return customers;
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
    public static void  updateEntry(String ID,String fullname, int age,String birthday, String address)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("fullname", fullname);
        updatedValues.put("age", age);
        updatedValues.put("date_of_birth", birthday);
        updatedValues.put("address", address);

        String where="ID = ?";
        db=dbHelper.getReadableDatabase();
        db.update(TABLE_NAME,updatedValues, where, new String[]{ID});
        db.close();
    }
}
