package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.PostStep;

public class NewRecipeStepAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<PostStep> postStepList;

    public NewRecipeStepAdapter(Activity activity, List<PostStep> postStepList) {
        this.activity = activity;
        this.postStepList = postStepList;
    }

    public void reloadData(List<PostStep> postStepList) {
        this.postStepList = postStepList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_new_recipe_step, parent, false);
        NewRecipeStepViewHolder holder = new NewRecipeStepViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewRecipeStepViewHolder viewHolder = (NewRecipeStepViewHolder) holder;
        PostStep model = postStepList.get(position);
        viewHolder.etNewRecipeStepDetail.setHint("Bước " + (position + 1));
        viewHolder.tvPostStepId.setText("" + (position + 1));
        viewHolder.ivRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postStepList.remove(holder.getAbsoluteAdapterPosition());
                reloadData(postStepList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postStepList.size();
    }

    public class NewRecipeStepViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText etNewRecipeStepDetail;
        TextView tvPostStepId;
        ImageView ivRemoveItem;
        public NewRecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);
            etNewRecipeStepDetail = itemView.findViewById(R.id.etNewRecipeStepDetail);
            tvPostStepId = itemView.findViewById(R.id.tvPostStepId);
            ivRemoveItem = itemView.findViewById(R.id.ivRemoveItem);
        }
    }
}
