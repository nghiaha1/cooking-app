package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

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
        String gradientDetail = viewHolder.etGradientDetail.getText().toString().toLowerCase(Locale.ROOT).trim();
        model.setDetail(gradientDetail);
        postGradientList.add(model);

        viewHolder.ivRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postGradientList.remove(holder.getAbsoluteAdapterPosition());
                reloadData(postGradientList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postGradientList.size();
    }

    public class NewRecipeGradientViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText etGradientDetail;
        ImageView ivRemoveItem;
        public NewRecipeGradientViewHolder(@NonNull View itemView) {
            super(itemView);
            etGradientDetail = itemView.findViewById(R.id.etGradientDetail);
            ivRemoveItem = itemView.findViewById(R.id.ivRemoveItem);
        }
    }
}
