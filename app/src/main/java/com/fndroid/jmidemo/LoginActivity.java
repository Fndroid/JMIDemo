package com.fndroid.jmidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private Button login;
    private Button register;
    private EditText password;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        JMessageClient.init(this);
        JPushInterface.setDebugMode(true);
        initViews();
    }

    private void initViews() {
        login = (Button) findViewById(R.id.login_btn_login);
        register = (Button) findViewById(R.id.login_btn_register);
        password = (EditText) findViewById(R.id.login_et_password);
        userName = (EditText) findViewById(R.id.login_et_userName);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String userName = this.userName.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        switch (v.getId()) {
            case R.id.login_btn_login:
                JMessageClient.login(userName, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Log.d(TAG, "gotResult() called with: " + "i = [" + i + "], s = [" + s + "]");
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                        if (i == 0) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                });
                break;
            case R.id.login_btn_register:
                JMessageClient.register(userName, password, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
