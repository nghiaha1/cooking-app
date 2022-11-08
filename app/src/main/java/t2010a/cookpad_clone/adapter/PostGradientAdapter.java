package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.PostDetailActivity;
import t2010a.cookpad_clone.model.home_client.PostGradient;

public class PostGradientAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<PostGradient> postGradientList;

    public PostGradientAdapter(PostDetailActivity activity, List<PostGradient> postGradientList) {
        this.activity = activity;
        this.postGradientList = postGradientList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_post_gradient, parent, false);
        PostGradientViewHolder holder = new PostGradientViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostGradientViewHolder viewHolder = (PostGradientViewHolder) holder;
        PostGradient model = postGradientList.get(position);
        viewHolder.tv_post_ingredient.setText(model.getDetail());
    }

    @Override
    public int getItemCount() {
        if (postGradientList != null) {
            return postGradientList.size();
        }
        return 0;
    }

    public class PostGradientViewHolder extends RecyclerView.ViewHolder {
        TextView tv_post_ingredient;
        public PostGradientViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_post_ingredient = itemView.findViewById(R.id.tv_post_ingredient);
        }
    }
}
