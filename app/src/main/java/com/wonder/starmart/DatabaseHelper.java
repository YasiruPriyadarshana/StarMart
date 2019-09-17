package com.wonder.starmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="StartMart.db";
    private static final String TABLE_NAME="UserLogin_table";
    private static final String COL_1="ID";
    private static final String COL_2="MOBILE_NUMBER";
    private static final String COL_3="NAME";
    private static final String COL_4="EMAIL";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, MOBILE_NUMBER INTEGER, NAME TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Boolean insertData(int MobileNum,String name,String email){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,MobileNum);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,email);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
}
