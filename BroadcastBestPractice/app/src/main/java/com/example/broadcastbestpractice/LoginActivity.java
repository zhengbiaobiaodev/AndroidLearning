package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText mAccountEditText;  //账号输入
    private EditText mPasswordEditText; //密码输入
    private Button mLoginButton; //登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAccountEditText = findViewById(R.id.account);
        mPasswordEditText = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccountEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                //如果账号是 admin 且密码是 12345 就认为登陆成功
                if (account.equals("admin") && password.equals("12345")) {
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
