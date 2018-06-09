package com.example.huyvan.calllog.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huyvan.calllog.R;
import com.example.huyvan.calllog.dao.SinhVienDAO;
import com.example.huyvan.calllog.dao.SinhVienDAOImpl;
import com.example.huyvan.calllog.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class EUpdateSinhVien extends AppCompatActivity implements View.OnClickListener{

    private Button searchBtn;
    private EditText eSearch;
    private ListView listView;
    private SinhVienDAO sinhVienDAO;
    private List<SinhVien> sinhVienList;
    private SinhVien sinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eupdate_sinh_vien);
        searchBtn = findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(this);
        eSearch = findViewById(R.id.e_search);
        listView = findViewById(R.id.list_view);
        sinhVienDAO = new SinhVienDAOImpl(this);
        sinhVienList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sinhVien = sinhVienList.get(i);
                Intent intent = new Intent(EUpdateSinhVien.this,UpdateSinhVien.class);
                intent.putExtra("sinhvien",sinhVien);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                sinhVien = sinhVienList.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(EUpdateSinhVien.this);
                builder.setMessage("DO YOU WANT TO REMOVE?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean result = sinhVienDAO.deleteSinhVien(sinhVien);
                        Toast.makeText(EUpdateSinhVien.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
                        setListView();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search_btn){
            setListView();
        }
    }

    private void setListView(){
        String name = eSearch.getText().toString();
        sinhVienList = sinhVienDAO.findByName(name);
        String[] arr = new String[sinhVienList.size()];
        int i = 0;
        for (SinhVien sinhVien : sinhVienList){
            arr[i] = sinhVien.getMasv() + " - " + sinhVien.getTensv();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        listView.setAdapter(adapter);
    }
}
