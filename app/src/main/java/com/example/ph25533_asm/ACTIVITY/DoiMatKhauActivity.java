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

public class DoiMatKhauActivity extends AppCompatActivity {

    private EditText edMatKhauCu, edMatKhauMoi, edNhapLaiMatKhauMoi;
    private TextView tvErrorMatKhauCu, tvErrorMatKhauMoi, tvErrorNhapLaiMatKhauMoi;
    private Button btnHuy, btnUpdate;
    private UserDao dao;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        anhxa();
        clearError();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearError();
                clearEditText();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edMatKhauCu.getText().toString();
                String newPass = edMatKhauMoi.getText().toString();
                String reNewPass = edNhapLaiMatKhauMoi.getText().toString();
                String id = String.valueOf(UserDao.list.get(UserDao.index).getId());
                if (checkValidate(oldPass, newPass, reNewPass)) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("password", newPass);
                    hashMap.put("id", id);
                    //Dữ liệu truyền vào theo ki
                    Toast.makeText(DoiMatKhauActivity.this, "Update mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    dao.updateData(DoiMatKhauActivity.this, Server.linkUpdatePass, hashMap);
                }
            }
        });

    }


    private boolean checkValidate(String oldPass, String newPass, String reNewPass) {
        // check mật khẩu cũ
        if (oldPass.isEmpty()) {
            tvErrorMatKhauCu.setText("Hãy nhập dầy đủ thông tin");
            return false;
        }
        if (checkPass(oldPass) == false) {
            tvErrorMatKhauCu.setText("mật khẩu cũ sai");
            return false;
        } else {
            tvErrorMatKhauCu.setText("");
        }
        // check mật khẩu mới
        if (newPass.isEmpty()) {
            tvErrorMatKhauMoi.setText("Hãy nhập dầy đủ thông tin");
            return false;
        } else {
            tvErrorMatKhauMoi.setText("");
        }
        if (newPass.equals(oldPass)) {
            tvErrorMatKhauMoi.setText("mật khẩu mới không được trùng với mật khẩu cũ");
            return false;
        }
        //check nhập lại
        if (reNewPass.isEmpty()) {
            tvErrorNhapLaiMatKhauMoi.setText("Hãy nhập dầy đủ thông tin");
            return false;
        } else {
            tvErrorNhapLaiMatKhauMoi.setText("");
        }
        if (!reNewPass.equals(newPass)) {
            tvErrorNhapLaiMatKhauMoi.setText("mật khẩu không trùng với mật khẩu mới");
            return false;
        }

        return true;
    }

    private boolean checkPass(String pass) {
        String strpass = dao.getList().get(UserDao.index).getPass();
        if (strpass.equals(pass)) {
            return true;
        }
        return false;
    }

    private void anhxa() {
        edMatKhauCu = findViewById(R.id.ed_changePassword_oldpassword);
        edMatKhauMoi = findViewById(R.id.ed_changePassword_newpassword);
        edNhapLaiMatKhauMoi = findViewById(R.id.ed_changePassword_renewpassword);
        tvErrorMatKhauCu = findViewById(R.id.tv_changePassword_error_oldpassword);
        tvErrorMatKhauMoi = findViewById(R.id.tv_changePassword_error_newpassword);
        tvErrorNhapLaiMatKhauMoi = findViewById(R.id.tv_changePassword_error_renewpassword);
        btnHuy = findViewById(R.id.btn_changePassword_huy);
        btnUpdate = findViewById(R.id.btn_changePassword_update);
        toolbar = findViewById(R.id.toolbar_changePassword);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dao = new UserDao();
        dao.getData(this, Server.linkselectAccount);
    }

    private void clearError() {
        tvErrorMatKhauCu.setText("");
        tvErrorMatKhauMoi.setText("");
        tvErrorNhapLaiMatKhauMoi.setText("");
    }

    private void clearEditText() {
        edMatKhauCu.setText("");
        edMatKhauMoi.setText("");
        edNhapLaiMatKhauMoi.setText("");
    }
}