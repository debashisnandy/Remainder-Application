package com.appsbyr.rexoni.meeting_reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class CustomSQLDB2 {
    public SQLiteDatabase sqlDB;
    final static String DataBaseName = "RemainderDataBase2";
    final static String TableName="RemainderListDone";
    final static String Column_Title ="Title";
    final static String Column_Description = "Description";
    final static String Column_DateAndTime = "DateAndTime";
    final static String Column_Date = "Dateee";
    final static String Column_DateInfo = "DateAndInfo";
    final static String Column_second="Secondsss";
    final static String Column_Time = "Timeee";
    final static  int DBVersion = 1;

    static final String create_table = "CREATE TABLE IF NOT EXISTS "+TableName+"(" +Column_Title+
            " TEXT NOT NULL,"+Column_Description+" TEXT NOT NULL,"
            +Column_DateAndTime +" DATETIME NOT NULL,"
            +Column_Date+" TEXT NOT NULL,"
            +Column_second+" INTEGER NOT NULL,"
            +Column_DateInfo+" INTEGER NOT NULL,"
            +Column_Time+" TEXT NOT NULL);";
    public static class DBqueryHelper extends SQLiteOpenHelper {

        Context context;

        DBqueryHelper(Context context){
            super(context, DataBaseName,null,DBVersion);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(create_table);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+TableName);
            onCreate(db);
        }
    }

    public CustomSQLDB2(Context context){
        DBqueryHelper dBqueryHelper = new DBqueryHelper(context);
        sqlDB = dBqueryHelper.getWritableDatabase();
    }

    public long inset(ContentValues contentValues){
        long ID =sqlDB.insert(TableName,"",contentValues);
        return ID;
    }

    public Cursor query(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TableName);
        Cursor cursor = sqLiteQueryBuilder.query(sqlDB,Projection,Selection,SelectionArgs,
                null,null,SortOrder);
        return cursor;
    }

    public Cursor SelectFirstRow(){
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM "+TableName+" ORDER BY "+
                Column_DateInfo+", "+Column_second+" LIMIT 1;",null);
        return  cursor;
    }

    public void delete(String Selection, String[] SelectionArgs){
        sqlDB.delete(TableName,Selection,SelectionArgs);
    }
}
