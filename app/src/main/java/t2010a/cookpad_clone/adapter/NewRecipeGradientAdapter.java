package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.PostGradient;

public class NewRecipeGradientAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<PostGradient> postGradientList;

    public NewRecipeGradientAdapter() {
    }

    public NewRecipeGradientAdapter(Activity activity, List<PostGradient> postGradientList) {
        this.activity = activity;
        this.postGradientList = postGradientList;
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
        viewHolder.et_new_recipe_gradient.setText(model.getDetail());
    }

    @Override
    public int getItemCount() {
        return postGradientList.size();
    }

    public class NewRecipeGradientViewHolder extends RecyclerView.ViewHolder {
        EditText et_new_recipe_gradient;
        public NewRecipeGradientViewHolder(@NonNull View itemView) {
            super(itemView);
            et_new_recipe_gradient = itemView.findViewById(R.id.et_new_recipe_gradient_detail);
        }
    }
}
