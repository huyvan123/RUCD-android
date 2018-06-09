package com.example.huyvan.calllog.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.example.huyvan.calllog.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyVan on 009/6/9/2018.
 */

public class SinhVienDAOImpl implements SinhVienDAO{
    private SQLiteConnector connector;

    public SinhVienDAOImpl(Context context) {
        connector = new SQLiteConnector(context);
    }

    @Override
    public boolean addSinhVien(SinhVien sinhVien) {
        try {
            SQLiteDatabase database = connector.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("masv",sinhVien.getMasv());
            values.put("tensv",sinhVien.getTensv());
            values.put("email",sinhVien.getEmail());
            System.out.println("Add: "+database.insert(SQLiteConnector.SINHVIEN_TABLE,null,values));
            database.close();
        }catch (SQLiteConstraintException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean editSinhVien(SinhVien sinhVien) {
        try {
            SQLiteDatabase database = connector.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tensv",sinhVien.getTensv());
            values.put("email",sinhVien.getEmail());
            database.update(SQLiteConnector.SINHVIEN_TABLE,values,"masv = ?",new String[]{sinhVien.getMasv()});
            database.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSinhVien(SinhVien sinhVien) {
        try {
            SQLiteDatabase database = connector.getWritableDatabase();
            database.delete(SQLiteConnector.SINHVIEN_TABLE,"masv = ?",new String[]{sinhVien.getMasv()});
            database.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<SinhVien> findAll() {
        List<SinhVien> list = new ArrayList<>();
        SQLiteDatabase database = connector.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ SQLiteConnector.SINHVIEN_TABLE,null);
        while (cursor.moveToNext()){
            SinhVien sinhVien = new SinhVien();
            sinhVien.setIdsv(cursor.getInt(cursor.getColumnIndex("idsv")));
            sinhVien.setMasv(cursor.getString(cursor.getColumnIndex("masv")));
            sinhVien.setTensv(cursor.getString(cursor.getColumnIndex("tensv")));
            sinhVien.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            list.add(sinhVien);
        }
        cursor.close();
        return list;
    }

    @Override
    public List<SinhVien> findByName(String name) {
        List<SinhVien> list = new ArrayList<>();
        SQLiteDatabase database = connector.getReadableDatabase();
        StringBuilder builder = new StringBuilder("SELECT * FROM ");
        builder.append(SQLiteConnector.SINHVIEN_TABLE).append(" as s ");
        builder.append(" WHERE s.tensv LIKE ? ");
        Cursor cursor = database.rawQuery(builder.toString(),new String[]{"%" + name + "%"});
        while (cursor.moveToNext()){
            SinhVien sinhVien = new SinhVien();
            sinhVien.setIdsv(cursor.getInt(cursor.getColumnIndex("idsv")));
            sinhVien.setMasv(cursor.getString(cursor.getColumnIndex("masv")));
            sinhVien.setTensv(cursor.getString(cursor.getColumnIndex("tensv")));
            sinhVien.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            list.add(sinhVien);
        }
        cursor.close();
        return list;
    }
}
