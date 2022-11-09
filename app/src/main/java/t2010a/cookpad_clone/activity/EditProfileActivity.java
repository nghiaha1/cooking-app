package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.client_model.User;
import t2010a.cookpad_clone.repository.Repository;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etProfileFullName, etProfileAddress,
            etProfilePhone, etProfileEmail, etProfileDetail;
    private Button btnUpdateUser;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private Repository repository;
    private User user;
    private String accessToken = "Bearer " + LocalDataManager.getAccessToken();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
        initData();
    }

    private void initView() {
        etProfileFullName = findViewById(R.id.etProfileFullName);
        etProfileAddress = findViewById(R.id.etProfileAddress);
        etProfilePhone = findViewById(R.id.etProfilePhone);
        etProfileEmail = findViewById(R.id.etProfileEmail);
        etProfileDetail = findViewById(R.id.etProfileDetail);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);
        toolbar = findViewById(R.id.toolbar);
        appBar = findViewById(R.id.appBar);

        btnUpdateUser.setOnClickListener(this);
    }

    private void initData() {
        user = LocalDataManager.getUserDetail();
        if (user == null) {
            etProfileFullName.setText("");
            etProfileAddress.setText("");
            etProfilePhone.setText("");
            etProfileEmail.setText("");
            etProfileDetail.setText("");
        } else {
            etProfileFullName.setText(user.getFullName());
            etProfileAddress.setText(user.getAddress());
            etProfilePhone.setText(user.getPhone());
            etProfileEmail.setText(user.getEmail());
            etProfileDetail.setText(user.getDetail());
        }

        setSupportActionBar(toolbar);
        appBar.setOutlineProvider(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void setBtnUpdateUser() {
        repository = Repository.getInstance();
        String fullName = etProfileFullName.getText().toString().toLowerCase(Locale.ROOT).trim();
        String phone = etProfilePhone.getText().toString().trim();
        String email = etProfileEmail.getText().toString().trim();
        String address = etProfileAddress.getText().toString().trim();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.getService().updateUser(user, user.getId(), accessToken).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 200) {
                            Log.d("TAG", "onResponse: change success");
                            Log.d("USER", "User: " + user);
                            Toast.makeText(EditProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.d("TAG", "onResponse:change fail");
                            Log.d("USER", "User: " + user);
                            Toast.makeText(EditProfileActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdateUser:
                setBtnUpdateUser();
                break;
            default:
                break;
        }
    }
}