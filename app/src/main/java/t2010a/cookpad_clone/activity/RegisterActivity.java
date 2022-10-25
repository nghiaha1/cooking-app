package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;
import t2010a.cookpad_clone.util.EditTextValidation;

public class RegisterActivity extends AppCompatActivity{
    EditText etUsername, etPassword, etRePassword;
    Button btnRegister;
    Repository repository;
    User user = new User();
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setBtnRegister();

        mScrollView.setVerticalScrollBarEnabled(false);
        mScrollView.setHorizontalScrollBarEnabled(false);
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etRePassword = findViewById(R.id.et_re_password);
        btnRegister = findViewById(R.id.btnRegister);
        mScrollView = findViewById(R.id.mScrollView);
    }


    private void setBtnRegister() {
        repository = Repository.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();
                String rePassword = etRePassword.getText().toString();
                
                if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fill all the information above!", Toast.LENGTH_SHORT).show();
                } else {
                    if (EditTextValidation.isValidUsername(username)) {
                        if (password.length() < 8) {
                            if (password.equals(rePassword)) {
                                user.setUsername(username);
                                user.setPassword(password);
                                user.setRePassword(rePassword);

                                repository.getService().registerUser(user).enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if (response.code() == 200) {
                                            Toast.makeText(RegisterActivity.this, "Register success. Http code " + response.code(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Register failed. Http code" + response.code(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {

                                    }
                                });

                            } else Toast.makeText(RegisterActivity.this,
                                    "Password is NOT match.", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(RegisterActivity.this, "Password required at least 8 characters.", Toast.LENGTH_SHORT).show();
                        
                        
                    } else Toast.makeText(RegisterActivity.this,
                            "Username invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}