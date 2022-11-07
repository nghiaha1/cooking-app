package t2010a.cookpad_clone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etProfileFullName, etProfileAddress,
            etProfilePhone, etProfileEmail, etProfileDetail;
    private Button btnUpdateUser;
    private Repository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
        initData();
        btnUpdateUser.setOnClickListener(this);
    }

    private void initView() {
        etProfileFullName = findViewById(R.id.etProfileFullName);
        etProfileAddress = findViewById(R.id.etProfileAddress);
        etProfilePhone = findViewById(R.id.etProfilePhone);
        etProfileEmail = findViewById(R.id.etProfileEmail);
        etProfileDetail = findViewById(R.id.etProfileDetail);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);
    }

    private void initData() {
        user = LocalDataManager.getUserDetail();
        etProfileFullName.setText(user.getFullName());
        etProfileAddress.setText(user.getAddress());
        etProfilePhone.setText(user.getPhone());
        etProfileEmail.setText(user.getEmail());
        etProfileDetail.setText(user.getDetail());
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
                repository.getService().updateUser(user, user.getId()).enqueue(new Callback<Optional<User>>() {
                    @Override
                    public void onResponse(Call<Optional<User>> call, Response<Optional<User>> response) {
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
                    public void onFailure(Call<Optional<User>> call, Throwable t) {

                    }
                });
            }
        });
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