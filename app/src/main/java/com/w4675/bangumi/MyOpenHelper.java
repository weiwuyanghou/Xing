package com.w4675.bangumi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context){

        super(context,"infor.db",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table information(" +
                "id integer primary key autoincrement, " +
                "name varchar(100)," +
                "sex varchar(150)," +
                "years varchar(100)," +
                "address varchar(400)," +
                "telephone varchar(100)," +
                "feiyong varchar(100000)," +
                "biaoshi varchar(10)," +
                "fangjian varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}