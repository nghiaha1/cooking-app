package t2010a.cookpad_clone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tv_to_reset_pass, tv_to_register;
    User user = new User();
    Repository repository;
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setBtnLogin();

        mScrollView.setVerticalScrollBarEnabled(false);
        mScrollView.setHorizontalScrollBarEnabled(false);

        btnLogin.setOnClickListener(this);
        tv_to_register.setOnClickListener(this);
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btnLogin);
        tv_to_reset_pass = findViewById(R.id.tv_to_reset_pass);
        tv_to_register = findViewById(R.id.tv_to_register);
        mScrollView = findViewById(R.id.mScrollView);
    }

    private void setBtnLogin() {
        repository = Repository.getInstance();
        String username = etUsername.getText().toString().toLowerCase().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Username or password is empty.", Toast.LENGTH_SHORT).show();
        } else {
            user.setUsername(username);
            user.setPassword(password);

            repository.getService().loginUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 200) {
                        LoginResponse loginResponse = new LoginResponse();

                        Log.d("TAG", "" + response.body());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed. Http code " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

    private void setTv_to_register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void setTv_to_reset_pass() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                setBtnLogin();
                break;
            case R.id.tv_to_register:
                setTv_to_register();
                break;
            case R.id.tv_to_reset_pass:
                setTv_to_reset_pass();
                break;
        }
    }
}