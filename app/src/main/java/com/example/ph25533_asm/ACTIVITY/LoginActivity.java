package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph25533_asm.DAO.UserDao;
import com.example.ph25533_asm.R;
import com.example.ph25533_asm.UNTIL.List;
import com.example.ph25533_asm.UNTIL.Server;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_signup;
    private Button btn_login;
    private EditText ed_userName, ed_passwork;
    private CheckBox chK_remember;
    private SharedPreferences sharedPreferences;
    private UserDao dao;
    private TextView tvErrorUserName, tvErrorPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();
        clearError();

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        readPrefer();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklogin();
            }
        });

    }

    private void anhXa(){
        tv_signup = findViewById(R.id.tv_signup);
        btn_login = findViewById(R.id.btn_login);
        ed_userName = findViewById(R.id.ed_login_username);
        ed_passwork = findViewById(R.id.ed_login_password);
        chK_remember = findViewById(R.id.login_check_remember);
        tvErrorUserName = findViewById(R.id.tv_login_error_username);
        tvErrorPass = findViewById(R.id.tv_login_error_password);
        dao = new UserDao();
        dao.getData(this, Server.linkselectAccount);
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
    }

    public static String name;

    private void checklogin(){
        String username = ed_userName.getText().toString().trim();
        String pass = ed_passwork.getText().toString().trim();

        name = username;//truyên name sang UserFragment

        writePrefer(username, pass, chK_remember.isChecked());
        if (checkValidate(username, pass)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Toast.makeText(this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
            dao.getID(this,Server.linkselectAccount,username);//nhap dung se getid = dao.index;
            List.listBill.clear(); //xoa sạch list bill cũ để set data cho lich su mua hang
            startActivity(intent);
        }
    }
    private boolean checkValidate(String username, String pass){
        if (username.isEmpty()){
            tvErrorUserName.setText("nhập user name");
            return false;
        }else {
            tvErrorUserName.setText("");
        }

        if (pass.isEmpty()){
            tvErrorPass.setText("nhập password");
            return false;
        }else {
            tvErrorPass.setText("");
        }

        if (checkUser(username, pass) == false){
            tvErrorUserName.setText("sai tài khoản hoặc mật khẩu");
            tvErrorPass.setText("sai tài khoản hoặc mật khẩu");
            return  false;
        }
        return true;
    }

    private boolean checkUser(String userName, String pass){
        for (int i=0; i<UserDao.list.size(); i++){
            String strUserName = UserDao.list.get(i).getUserName();
            String strPass = UserDao.list.get(i).getPass();
            if (strUserName.equals(userName) && strPass.equals(pass)){
                UserDao.index = i;
                return true;
            }
        }
        return false;
    }

    private void writePrefer(String username, String passwork , Boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", username);
        editor.putString("PASSWORD", passwork);
        editor.putBoolean("STATUS", status);
        editor.commit();
    }

    private void readPrefer(){
        String strUserName = sharedPreferences.getString("USERNAME", null);
        String strPassword = sharedPreferences.getString("PASSWORD", null);
        Boolean status = sharedPreferences.getBoolean("STATUS", false);

        chK_remember.setChecked(status);
        if (chK_remember.isChecked()){
            ed_userName.setText(strUserName);
            ed_passwork.setText(strPassword);
        }
    }
    private void clearError(){
        tvErrorUserName.setText("");
        tvErrorPass.setText("");
    }

    @Override
    public void onBackPressed() {

    }
}