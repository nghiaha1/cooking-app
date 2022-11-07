package t2010a.cookpad_clone.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.EditProfileActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View itemView;
    TextView tvEditProfile, tvResetPass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(itemView);
        tvEditProfile.setOnClickListener(this);
        return itemView;
    }

    private void initView(View itemView) {
        tvEditProfile = itemView.findViewById(R.id.tvEditProfile);
        tvResetPass = itemView.findViewById(R.id.tvResetPass);
    }

    private void setTvEditProfile() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEditProfile:
                setTvEditProfile();
                break;
            case R.id.tvResetPass:

                break;
            default:

                break;
        }
    }
}