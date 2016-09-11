package com.example.user.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String DATABASE_NAME = "MY_DATABASE.db";
    String TABLE_NAME = "MY_TABLE";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT, CITY TEXT);");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error in creating table",Toast.LENGTH_LONG).show();
        }

        try{
            db.execSQL("INSERT INTO " + TABLE_NAME + " (NAME, CITY) VALUES ('ALIREZA', 'SHIRAZ')");// + VALUES (0 , 'ALIREZA' , 'SHIRAZ'
            Toast.makeText(getApplicationContext()," no error in adding row",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error in adding row",Toast.LENGTH_LONG).show();
        }

        try{
            Cursor allrows = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
            allrows.moveToFirst();
            do{
                String ID = allrows.getString(allrows.getColumnIndex("ID"));
                String NAME = allrows.getString(1);
                String CITY = allrows.getString(2);
                Toast.makeText(getApplicationContext(),ID + " " + NAME + " " + CITY, Toast.LENGTH_LONG).show();
            }while (allrows.moveToNext());
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Error in showing row",Toast.LENGTH_LONG).show();
        }
        db.close();
    }
}
