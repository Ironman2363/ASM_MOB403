package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ph25533_asm.ADAPTER.BillAdrapter;
import com.example.ph25533_asm.DAO.BillDao;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.Server;

public class LichsumuahangActivity extends AppCompatActivity {

    private ListView lvLichmuahang;
    private BillAdrapter billAdrapter;
    private BillDao billDao;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsumuahang);
        //anh xa---
        lvLichmuahang = findViewById(R.id.lv_lichsumuahang);
        toolbar = findViewById(R.id.toolbar_lichsumuahang);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int id_user = getIntent().getIntExtra("id",0);
        Toast.makeText(this, ""+id_user, Toast.LENGTH_SHORT).show();

        billAdrapter = new BillAdrapter(this);
        billDao = new BillDao();
        billDao.getBill(this, Server.linkSelectBill,id_user,billAdrapter);
        lvLichmuahang.setAdapter(billAdrapter);
    }
}