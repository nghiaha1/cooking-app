package t2010a.cookpad_clone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.EditProfileActivity;
import t2010a.cookpad_clone.activity.LoginActivity;
import t2010a.cookpad_clone.activity.MainActivity;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.client_model.User;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View itemView;
    private TextView tvFullName, tvUsername, tvEmail;
    private ShapeableImageView ivAvatar;
    private LinearLayout layoutEditProfile, layoutResetPass, btnLogout, layoutManagePost;

    private User user;


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
        tvFullName = itemView.findViewById(R.id.tvFullName);
        tvUsername = itemView.findViewById(R.id.tvUsername);
        tvEmail = itemView.findViewById(R.id.tvPostDescription);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        layoutEditProfile = itemView.findViewById(R.id.layoutEditProfile);
        layoutResetPass = itemView.findViewById(R.id.layoutResetPass);
        btnLogout = itemView.findViewById(R.id.btnLogout);
        layoutManagePost = itemView.findViewById(R.id.layoutManagePost);

        btnLogout.setOnClickListener(this);
        layoutEditProfile.setOnClickListener(this);
        layoutResetPass.setOnClickListener(this);
        layoutManagePost.setOnClickListener(this);
    }

    private void initData() {
        user = LocalDataManager.getUserDetail();
        if (user != null) {
            tvFullName.setText(user.getFullName());
            tvUsername.setText(user.getUsername());
            tvEmail.setText(user.getEmail());
            tvFullName.setText(user.getFullName());
            tvFullName.setText(user.getFullName());
            Glide.with(this).load(user.getAvatar()).into(ivAvatar);
        }
    }

    private void setLayoutEditProfile() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }

    private void setLayoutResetPass() {

    }

    private void setLayoutManagePost() {

    }

    private void setBtnLogout() {
        LocalDataManager.clearData();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                setBtnLogout();
                break;
            case R.id.layoutEditProfile:
                setLayoutEditProfile();
                break;
            case R.id.layoutResetPass:
                setLayoutResetPass();
                break;
            case R.id.layoutManagePost:
                setLayoutManagePost();
                break;

            default:
                break;
        }
    }
}