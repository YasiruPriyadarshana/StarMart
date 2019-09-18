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

    private static final String TABLE_NAME2="UserReview_table";
    private static final String COLM_1="RID";
    private static final String COLM_2="RNAME";
    private static final String COLM_3="DESCRIPTION";
    private static final String COLM_4="RATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, MOBILE_NUMBER INTEGER, NAME TEXT, EMAIL TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME2 + "(RID INTEGER PRIMARY KEY AUTOINCREMENT, RNAME TEXT, DESCRIPTION TEXT, RATE FLOAT)");
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
    public Cursor getIDofUser(String name){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " where NAME = '" +name + "'" , null);
        return res;
    }

    public boolean updateData(String id,String number,String name,String email){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,number);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,email);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ id });
        return true;
    }


    //for review
    public Boolean insertDataReview(String name,String description,float rate){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLM_2,name);
        contentValues.put(COLM_3,description);
        contentValues.put(COLM_4,rate);

        long result = sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllDataReview(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return res;
    }
    public Cursor getIDofReview(String rname){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME2 + " where RNAME = '" +rname + "'" , null);
        return res;
    }
}
