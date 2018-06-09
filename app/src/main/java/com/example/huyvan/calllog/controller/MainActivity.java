package com.example.huyvan.calllog.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.huyvan.calllog.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addBtn;
    private Button eUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.addBtn);
        eUpdate = findViewById(R.id.update_editBtn);
        addBtn.setOnClickListener(this);
        eUpdate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addBtn){
            Intent intent = new Intent(this,AddSinhVien.class);
            startActivity(intent);
        }else if(view.getId() == R.id.update_editBtn){
            Intent intent = new Intent(this,EUpdateSinhVien.class);
            startActivity(intent);
        }
    }

}
