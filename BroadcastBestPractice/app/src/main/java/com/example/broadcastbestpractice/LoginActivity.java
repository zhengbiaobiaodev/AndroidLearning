package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mAccountEditText;  //账号输入
    private EditText mPasswordEditText; //密码输入
    private Button mLoginButton; //登录按钮
    private CheckBox mCheckBox; //记住密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mAccountEditText = findViewById(R.id.account);
        mPasswordEditText = findViewById(R.id.password);
        mCheckBox = findViewById(R.id.remember_pass);
        mLoginButton = findViewById(R.id.login);
        final boolean isRemember = mSharedPreferences
                .getBoolean("remember_password", false);
        if (isRemember) {
            //将账号和密码都设置到文本框中
            String account = mSharedPreferences.getString("account", "");
            String password = mSharedPreferences.getString("password", "");
            mAccountEditText.setText(account);
            mPasswordEditText.setText(password);
            mAccountEditText.setSelection(account.length());
            mPasswordEditText.setSelection(password.length());
            mCheckBox.setChecked(true);
        }
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccountEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                //如果账号是 admin 且密码是 12345 就认为登陆成功
                if (account.equals("admin") && password.equals("12345")) {

                    mEditor = mSharedPreferences.edit();
                    if (mCheckBox.isChecked()) { //检查复选框是否被选中
                        mEditor.putBoolean("remember_password", true);
                        mEditor.putString("account", account);
                        mEditor.putString("password", password);
                    } else {
                        mEditor.clear();
                    }
                    mEditor.apply();

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
