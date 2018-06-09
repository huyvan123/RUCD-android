package com.example.huyvan.calllog.dao;

import com.example.huyvan.calllog.model.SinhVien;

import java.util.List;

/**
 * Created by HuyVan on 009/6/9/2018.
 */

public interface SinhVienDAO {
    public boolean addSinhVien(SinhVien sinhVien);
    public boolean editSinhVien(SinhVien sinhVien);
    public boolean deleteSinhVien(SinhVien sinhVien);
    public List<SinhVien> findAll();
    public List<SinhVien> findByName(String name);
}
