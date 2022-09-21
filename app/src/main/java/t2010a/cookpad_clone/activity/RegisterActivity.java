package t2010a.cookpad_clone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.UserRepository;
import t2010a.cookpad_clone.util.EditTextValidation;

public class RegisterActivity extends AppCompatActivity{
    EditText etUsername, etPassword, etRePassword, etAddress, etPhone;
    TextView tvToLogin;
    Button btnRegister;
    UserRepository userRepository;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setTvToLogin();
        setBtnRegister();
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
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnRegister() {
        userRepository = UserRepository.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String rePassword = etRePassword.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                
                if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fill all the information above!", Toast.LENGTH_SHORT).show();
                } else {
                    if (EditTextValidation.isValidUsername(username)) {
                        if (password.equals(rePassword)) {
                           user.setUsername(username);
                           user.setPassword(password);
                           user.setRePassword(rePassword);
                           user.setAddress(address);
                           user.setPhone(phone);

                            userRepository.getService().registerUser(user).enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    Toast.makeText(RegisterActivity.this, "Register success.", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Toast.makeText(RegisterActivity.this, "ERROR.", Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else Toast.makeText(RegisterActivity.this,
                                "Password is NOT match.", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(RegisterActivity.this,
                            "Username invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}