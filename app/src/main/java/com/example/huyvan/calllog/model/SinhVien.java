package com.example.huyvan.calllog.model;

import java.io.Serializable;

/**
 * Created by HuyVan on 009/6/9/2018.
 */

public class SinhVien implements Serializable{
    private Integer idsv;
    private String masv;
    private String tensv;
    private String email;

    public SinhVien() {
    }

    public Integer getIdsv() {
        return idsv;
    }

    public void setIdsv(Integer idsv) {
        this.idsv = idsv;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
