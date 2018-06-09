package com.example.huyvan.calllog.controller;

import android.content.Intent;
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

public class UpdateSinhVien extends AppCompatActivity implements View.OnClickListener{

    private Button update;
    private EditText eTensv;
    private EditText eEmail;
    private EditText eMasv;
    private SinhVienDAO sinhVienDAO;
    private SinhVien sinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);
        Intent intent = getIntent();
        sinhVien = (SinhVien) intent.getSerializableExtra("sinhvien");
        update = findViewById(R.id.update);
        update.setOnClickListener(this);
        eTensv = findViewById(R.id.u_tensv);
        eEmail = findViewById(R.id.u_email);
        eMasv = findViewById(R.id.u_masv);
        sinhVienDAO = new SinhVienDAOImpl(this);
        setAttribute();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.update){
            String name = eTensv.getText().toString();
            String email = eEmail.getText().toString();
            sinhVien.setTensv(name);
            sinhVien.setEmail(email);
            boolean result = sinhVienDAO.editSinhVien(sinhVien);
            Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,EUpdateSinhVien.class);
            startActivity(intent);
        }
    }

    private void setAttribute(){
        eMasv.setText(sinhVien.getMasv());
        eTensv.setText(sinhVien.getTensv());
        eEmail.setText(sinhVien.getEmail());
    }
}
