package com.example.hp.signupandsigninusingdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 10/18/2017.
 */

public class Dbhelper extends SQLiteOpenHelper {

    private static final String DB_NAME="info.db";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME ="INFORMATION" ;
    private static final String COL1 ="NAME" ;
    private static final String COL2 ="MOBILE" ;
    private static final String COL3 ="PASSWORD" ;


    public Dbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ( " + COL1+" TEXT , "+COL2+" INTEGER PRIMARY KEY , "+COL3+" TEXT "+  " ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertinfo(String name,String mobile,String password)
    {
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase db=this.getWritableDatabase();
        contentValues.put(COL1,name);
        contentValues.put(COL2,mobile);
        contentValues.put(COL3,password);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
