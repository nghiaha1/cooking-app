package t2010a.cookpad_clone.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.user.UpdateUser;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;


public class ProfileFragment extends Fragment {
    private View itemView;
    private EditText etProfileFullName, etProfileAddress,
            etProfilePhone, etProfileEmail, etProfileDetail;
    private Button btnUpdateUser;
    private Repository repository;
    private User user;
    private UpdateUser updateUser = new UpdateUser();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(itemView);
        initData();
        setBtnUpdateUser();
        return itemView;
    }

    private void initView(View itemView) {
        etProfileFullName = itemView.findViewById(R.id.etProfileFullName);
        etProfileAddress = itemView.findViewById(R.id.etProfileAddress);
        etProfilePhone = itemView.findViewById(R.id.etProfilePhone);
        etProfileEmail = itemView.findViewById(R.id.etProfileEmail);
        etProfileDetail = itemView.findViewById(R.id.etProfileDetail);
        btnUpdateUser = itemView.findViewById(R.id.btnUpdateUser);
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
        String accessToken = LocalDataManager.getAccessToken();
        repository = Repository.getInstance();
        String fullName = etProfileFullName.getText().toString().toLowerCase(Locale.ROOT).trim();
//        String phone = etProfilePhone.getText().toString().trim();
//        String email = etProfileEmail.getText().toString().trim();
//        String address = etProfileAddress.getText().toString().trim();

        updateUser.setFullName(fullName);
//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setAddress(address);

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.getService().updateUser(updateUser, user.getId(), accessToken).enqueue(new Callback<UpdateUser>() {
                    @Override
                    public void onResponse(Call<UpdateUser> call, Response<UpdateUser> response) {
                        if (response.code() == 200) {
                            Log.d("TAG", "onResponse: change success");
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.d("TAG", "onResponse:change fail");
                            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateUser> call, Throwable t) {

                    }
                });
            }
        });
    }
}