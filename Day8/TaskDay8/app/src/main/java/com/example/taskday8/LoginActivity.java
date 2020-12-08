package com.example.taskday8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private MaterialButton bt_login;
    private TextInputEditText et_username,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }
    private void initView(){
        bt_login=findViewById(R.id.bt_login);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
    }

    private void doLogin(){
        if (!et_username.getText().toString().equals("")&&!et_password.getText().toString().equals("")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Mohon isi Username dan Password",Toast.LENGTH_LONG).show();
        }
    }
}