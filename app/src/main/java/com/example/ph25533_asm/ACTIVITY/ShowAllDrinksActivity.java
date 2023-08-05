package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.ph25533_asm.ADAPTER.AllProductAdapter;
import com.example.ph25533_asm.DAO.FoodDao;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.Server;

public class ShowAllDrinksActivity extends AppCompatActivity {

    private AllProductAdapter adapter;
    private FoodDao dao;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_foods);

        toolbar = findViewById(R.id.toolbar_showallfoods);
        recyclerView = findViewById(R.id.recycelview_showallfoods);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(" All food");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dao = new FoodDao();
        adapter = new AllProductAdapter(this);
        dao.getall(this, Server.linkGetDoAn, adapter);
        GridLayoutManager managerdrinks = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(managerdrinks);
        recyclerView.setAdapter(adapter);
    }
}