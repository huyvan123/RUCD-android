package com.example.huyvan.calllog.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HuyVan on 009/6/9/2018.
 */

public class SQLiteConnector extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Quanlysinhvien";
    private static final Integer DATABASE_VERSION = 1;
    public static final String SINHVIEN_TABLE = "sinhvien";

    public SQLiteConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getCreateSinhVienTable().toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ SINHVIEN_TABLE);
        onCreate(sqLiteDatabase);
    }

    private StringBuilder getCreateSinhVienTable(){
        StringBuilder builder = new StringBuilder("CREATE TABLE ");
        builder.append(SINHVIEN_TABLE).append(" ( ");
        builder.append(" idsv INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append(" masv TEXT NOT NULL UNIQUE, ");
        builder.append(" tensv TEXT, ");
        builder.append(" email TEXT ");
        builder.append(" ) ");
        return builder;
    }
}
