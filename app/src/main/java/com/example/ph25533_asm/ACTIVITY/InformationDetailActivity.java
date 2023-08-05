package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ph25533_asm.DAO.UserDao;
import com.example.ph25533_asm.MODEL.User;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.Server;

public class InformationDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout PhoneNumber, Name, Email;
    private TextView tvUsername, tvPhonenumber, tvName, tvEmail;
    private UserDao dao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);

        anhXa();
        setToolbar();
        setInformation();
    }

    private void setInformation(){
        tvUsername.setText(UserDao.list.get(UserDao.index).getUserName());
        tvPhonenumber.setText(UserDao.list.get(UserDao.index).getPhoneNumber());
        tvName.setText(UserDao.list.get(UserDao.index).getFullName());
        tvEmail.setText(UserDao.list.get(UserDao.index).getEmail());
    }

    private void anhXa(){
        toolbar = findViewById(R.id.information_toolbar);
        PhoneNumber = findViewById(R.id.information_phonenumber);
        Name = findViewById(R.id.information_name);
        Email = findViewById(R.id.information_email);
        tvUsername = findViewById(R.id.tv_information_username);
        tvPhonenumber = findViewById(R.id.tv_information_phonenumber);
        tvName = findViewById(R.id.tv_information_name);
        tvEmail = findViewById(R.id.tv_information_email);
        dao = new UserDao();
        dao.getData(this, Server.linkselectAccount);
        user = new User();
    }
    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}