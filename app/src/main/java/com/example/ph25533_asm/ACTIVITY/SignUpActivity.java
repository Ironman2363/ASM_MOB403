package com.example.ph25533_asm.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText edUserName, edFullName, edEmail, edPhoneNumber, edPassword,edRePassword;
    private TextView tvErrorUserName, tvErrorFullName, tvErrorEmail, tvErrorPhoneNumber, tvErrorPass, tvErrorRePass , tvErrorNoInformation;
    private Button btnSignUp;
    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anhxa();
        clearError();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionCreateSignup();
            }
        });

    }

    private void actionCreateSignup(){
        String userName = edUserName.getText().toString();
        String fullName = edFullName.getText().toString();
        String email = edEmail.getText().toString();
        String phoneNumber = edPhoneNumber.getText().toString();
        String password = edPassword.getText().toString();

        if (checkValidate()){
            HashMap<String, String > hashMap = new HashMap<>();
            hashMap.put("userName", userName);
            hashMap.put("fullName", fullName);
            hashMap.put("email", email);
            hashMap.put("phoneNumber", phoneNumber);
            hashMap.put("password", password);
            dao.insertData(this, Server.linkInsertAccount, hashMap);
            Toast.makeText(this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void anhxa(){
        edUserName = findViewById(R.id.ed_signup_username);
        edFullName = findViewById(R.id.ed_signup_fullname);
        edEmail = findViewById(R.id.ed_signup_email);
        edPhoneNumber = findViewById(R.id.ed_signup_phonenumber);
        edPassword = findViewById(R.id.ed_signup_pass);
        edRePassword = findViewById(R.id.ed_signup_repass);
        btnSignUp = findViewById(R.id.btn_signup);
        tvErrorUserName = findViewById(R.id.tv_signup_error_username);
        tvErrorFullName = findViewById(R.id.tv_signup_error_fullname);
        tvErrorEmail = findViewById(R.id.tv_signup_error_email);
        tvErrorPhoneNumber = findViewById(R.id.tv_signup_error_phonenumber);
        tvErrorPass = findViewById(R.id.tv_signup_error_pass);
        tvErrorRePass = findViewById(R.id.tv_signup_error_repass);
        tvErrorNoInformation = findViewById(R.id.tv_signup_error_no_information);
        dao = new UserDao();
        dao.getData(this, Server.linkselectAccount);

    }

    private boolean checkValidate(){
        String userName = edUserName.getText().toString();
        String fullName = edFullName.getText().toString();
        String email = edEmail.getText().toString();
        String phoneNumber = edPhoneNumber.getText().toString();
        String password = edPassword.getText().toString();
        String repass = edRePassword.getText().toString();
        //check trống
        if (userName.isEmpty() || email.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || repass.isEmpty()){
            tvErrorNoInformation.setText("bạn chưa nhập đủ thông tin");
            return false;
        }
        // check user trùng
        if (checkUserName(userName) == true){
            tvErrorUserName.setText("đã tồn tại user name này");
            return false;
        }else {
            tvErrorUserName.setText("");
        }
        //check email trùng
        if (checkEmail(email) == true){
            tvErrorEmail.setText("email đã được sử dụng");
            return false;
        }else {
            tvErrorEmail.setText("");
        }
        //check sđt trùng
        if (checkPhonenumber(phoneNumber) == true) {
            tvErrorPhoneNumber.setText("số điện thoại đã được sử dụng");
            return false;
        }else {
            tvErrorPhoneNumber.setText("");
        }
        // check pass
        if (password.equals(repass) == false){
            tvErrorRePass.setText("sai mật khẩu");
            return false;
        }
        return  true;
    }

    private boolean checkEmail(String email){
        for (int i=0; i<dao.getList().size(); i++){
            String email_u = dao.getList().get(i).getEmail();
            if (email.equals(email_u)){
                return true;
            }
        }
        return false;
    }

    private boolean checkPhonenumber(String phone){
        for (int i=0; i<dao.getList().size(); i++){
            String phoneNumber_u = dao.getList().get(i).getPhoneNumber();
            if (phone.equals(phoneNumber_u)){
                return  true;
            }
        }
        return false;
    }
    private boolean checkUserName(String username){
        for (int i=0; i<dao.getList().size(); i++){
            String username_u = dao.getList().get(i).getUserName();
            if (username.equals(username_u)){
                return  true;
            }
        }
        return false;
    }

    private void clearError(){
        tvErrorUserName.setText("");
        tvErrorNoInformation.setText("");
        tvErrorFullName.setText("");
        tvErrorEmail.setText("");
        tvErrorPhoneNumber.setText("");
        tvErrorPass.setText("");
        tvErrorRePass.setText("");
    }
}