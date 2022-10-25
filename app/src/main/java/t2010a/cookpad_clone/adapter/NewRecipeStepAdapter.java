package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    public void reloadData() {
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
        viewHolder.tv_new_recipe_step_id.setText(Integer.toString(model.getId()));
        viewHolder.et_new_recipe_step_detail.setText(model.getDetail());
    }

    @Override
    public int getItemCount() {
        return postStepList.size();
    }

    public class NewRecipeStepViewHolder extends RecyclerView.ViewHolder {
        TextView tv_new_recipe_step_id;
        EditText et_new_recipe_step_detail;
        public NewRecipeStepViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_new_recipe_step_id = itemView.findViewById(R.id.tv_new_recipe_step_id);
            et_new_recipe_step_detail = itemView.findViewById(R.id.et_new_recipe_step_detail);
        }
    }
}
