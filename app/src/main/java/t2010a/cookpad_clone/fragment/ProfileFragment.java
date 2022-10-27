package t2010a.cookpad_clone.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.user.User;


public class ProfileFragment extends Fragment {
    View itemView;
    EditText et_profile_username, et_profile_fullName, et_profile_address,
            et_profile_phone, et_profile_email, et_profile_detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(itemView);
        initData();
        return itemView;
    }

    private void initView(View itemView) {
        et_profile_username = itemView.findViewById(R.id.et_profile_username);
        et_profile_fullName = itemView.findViewById(R.id.et_profile_fullName);
        et_profile_address = itemView.findViewById(R.id.et_profile_address);
        et_profile_phone = itemView.findViewById(R.id.et_profile_phone);
        et_profile_email = itemView.findViewById(R.id.et_profile_email);
        et_profile_detail = itemView.findViewById(R.id.et_profile_detail);
    }

    private void initData() {
        User user = LocalDataManager.getUserDetail();
        et_profile_username.setText(user.getUsername());
        et_profile_fullName.setText(user.getFullName());
        et_profile_address.setText(user.getAddress());
        et_profile_phone.setText(user.getPhone());
        et_profile_email.setText(user.getEmail());
        et_profile_detail.setText(user.getDetail());
    }
}