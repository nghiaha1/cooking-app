package t2010a.cookpad_clone.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.NewRecipeActivity;
import t2010a.cookpad_clone.activity.NewTipActivity;

public class NewPostFragment extends Fragment implements View.OnClickListener {
    View itemView;
    LinearLayout layout_new_recipe, layout_new_tip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_new_post, container, false);
        initView(itemView);
        layout_new_recipe.setOnClickListener(this);
        layout_new_tip.setOnClickListener(this);

        return itemView;
    }

    private void initView(View itemView) {
        layout_new_recipe = itemView.findViewById(R.id.layout_new_recipe);
        layout_new_tip = itemView.findViewById(R.id.layout_new_tip);
    }

    private void setLayout_new_recipe() {
        Intent intent = new Intent(getActivity(), NewRecipeActivity.class);
        startActivity(intent);
    }

    private void setLayout_new_tip() {
        Intent intent = new Intent(getActivity(), NewTipActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_new_recipe:
                setLayout_new_recipe();
                break;
            case R.id.layout_new_tip:
                setLayout_new_tip();
                break;
            default:
                break;
        }
    }
}