package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph25533_asm.DAO.UserDao;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.Server;

import java.util.HashMap;

public class UpdateAddressActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText edUpdateAdress;
    private Button btnUpdate;
    private TextView errorAddress;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);

        toolbar = findViewById(R.id.updateAddress_toolbar);
        edUpdateAdress = findViewById(R.id.ed_updateAddress);
        errorAddress = findViewById(R.id.tv_error_updateAddress);
        btnUpdate = findViewById(R.id.btn_updateAddress);

        dao= new UserDao();

        errorAddress.setText(" ");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = edUpdateAdress.getText().toString();
                String id = String.valueOf(UserDao.list.get(UserDao.index).getId());
                if (checkValidate(address)){
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", id);
                    hashMap.put("address", address);
                    Toast.makeText(UpdateAddressActivity.this, "thêm địa chỉ thành công", Toast.LENGTH_SHORT).show();
                    dao.updateData(UpdateAddressActivity.this, Server.linkUpdateAddress, hashMap);
                    finish();
                }
            }
        });

    }

    private boolean checkValidate(String address){
        if (address.isEmpty()){
            errorAddress.setText("nhâp đủ thông tin");
            return false;
        }
        return true;
    }
}