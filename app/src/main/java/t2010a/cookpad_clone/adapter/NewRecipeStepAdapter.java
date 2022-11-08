package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Locale;

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
        viewHolder.etStepDetail.setHint("Bước " + (position + 1));
        viewHolder.tvPostStepId.setText("" + (position + 1));
//        String stepDetail = viewHolder.etStepDetail.getText().toString().toLowerCase(Locale.ROOT).trim();
//        model.setDetail(stepDetail);
//        postStepList.add(model);

//        postStepList.add(model);
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
        TextInputEditText etStepDetail;
        TextView tvPostStepId;
        ImageView ivRemoveItem;
        public NewRecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);
            etStepDetail = itemView.findViewById(R.id.etStepDetail);
            tvPostStepId = itemView.findViewById(R.id.tvPostStepId);
            ivRemoveItem = itemView.findViewById(R.id.ivRemoveItem);

            etStepDetail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    postStepList.get(getAdapterPosition()).setDetail(etStepDetail.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
