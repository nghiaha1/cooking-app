package t2010a.cookpad_clone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import t2010a.cookpad_clone.R;

public class RegisterActivity extends AppCompatActivity{
    EditText etUsername, etPassword, etRePassword, etAddress, etPhone;
    TextView tvToLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setTvToLogin();8
    }

    private void initView() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        tvToLogin = findViewById(R.id.tvToLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void setTvToLogin() {
        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setBtnRegister() {

    }
}