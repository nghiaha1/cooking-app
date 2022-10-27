package t2010a.cookpad_clone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.network.ApiService;
import t2010a.cookpad_clone.repository.Repository;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvToResetPassword, tv_to_register;
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
        tvToResetPassword = findViewById(R.id.tv_to_reset_password);
        mScrollView = findViewById(R.id.mScrollView);
        tv_to_register = findViewById(R.id.tv_to_register);
    }

    private void getUserDetail() {
        repository.getService().getUser("Bearer " + LocalDataManager.getAccessToken()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                LocalDataManager.setUserDetail(response.body());
                Log.d("TAG", "User " + response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
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

            repository.getService().loginUser(user).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.code() == 200) {
                        LoginResponse loginResponse = new LoginResponse(response.body().getAccessToken(), response.body().getRefreshToken());
                        LocalDataManager.setAccessToken(response.body().getAccessToken());
                        getUserDetail();

                        Log.d("TAG", "Response " + loginResponse);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed. Http code " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });
        }
    }

    private void setTv_to_register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                setBtnLogin();
                break;
            case R.id.tv_to_register:
                setTv_to_register();
                break;
        }
    }
}