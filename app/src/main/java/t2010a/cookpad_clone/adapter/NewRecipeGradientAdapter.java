package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.NewRecipeActivity;
import t2010a.cookpad_clone.model.home_client.PostGradient;

public class NewRecipeGradientAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<PostGradient> postGradientList;

    public NewRecipeGradientAdapter(NewRecipeActivity activity, List<PostGradient> postGradientList) {
        this.activity = activity;
        this.postGradientList = postGradientList;
    }

    public void reloadData(List<PostGradient> postGradientList) {
        this.postGradientList = postGradientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_new_recipe_gradient, parent, false);
        NewRecipeGradientViewHolder holder = new NewRecipeGradientViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewRecipeGradientViewHolder viewHolder = (NewRecipeGradientViewHolder) holder;
        PostGradient model = postGradientList.get(position);
        viewHolder.etGradientDetail.setHint("Nguyên liệu " + (position + 1));

        viewHolder.ivRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postGradientList.remove(holder.getAdapterPosition());
                reloadData(postGradientList);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (postGradientList != null) {
            return postGradientList.size();
        }
        return 0;
    }

    public class NewRecipeGradientViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText etGradientDetail;
        ImageView ivRemoveItem;

        public NewRecipeGradientViewHolder(@NonNull View itemView) {
            super(itemView);
            etGradientDetail = itemView.findViewById(R.id.etGradientDetail);
            ivRemoveItem = itemView.findViewById(R.id.ivRemoveItem);

            etGradientDetail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    postGradientList.get(getAdapterPosition()).setDetail(etGradientDetail.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
