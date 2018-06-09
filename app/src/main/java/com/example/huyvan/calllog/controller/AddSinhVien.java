package com.example.huyvan.calllog.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huyvan.calllog.R;
import com.example.huyvan.calllog.dao.SinhVienDAO;
import com.example.huyvan.calllog.dao.SinhVienDAOImpl;
import com.example.huyvan.calllog.model.SinhVien;

import java.util.regex.Pattern;

public class AddSinhVien extends AppCompatActivity implements View.OnClickListener{

    private Button add;
    private EditText masv;
    private EditText tensv;
    private EditText email;
    private SinhVienDAO sinhVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);
        sinhVienDAO = new SinhVienDAOImpl(this);
        add = findViewById(R.id.add);
        masv = findViewById(R.id.masv);
        tensv = findViewById(R.id.tensv);
        email = findViewById(R.id.email);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String ma = masv.getText().toString();
        Pattern pattern = Pattern.compile("[BN]{1}+[0-9]{2}+(?:CD|DC)+[A-Z]{2}+[0-9]{3}");
        boolean matcher = pattern.matcher(ma).matches();
        if (ma == null || ma.trim().equals("")){
            Toast.makeText(this, "Yeu cau nhap ma sinh vien", Toast.LENGTH_SHORT).show();
        }else if(matcher == false){
            Toast.makeText(this, "Ma Sinh Vien khong hop le", Toast.LENGTH_SHORT).show();
        }
        else {
            String ten = tensv.getText().toString();
            String e = email.getText().toString();
            SinhVien sinhVien = new SinhVien();
            sinhVien.setMasv(ma);
            sinhVien.setTensv(ten);
            sinhVien.setEmail(e);
            boolean result = sinhVienDAO.addSinhVien(sinhVien);
            Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        }
    }
}
