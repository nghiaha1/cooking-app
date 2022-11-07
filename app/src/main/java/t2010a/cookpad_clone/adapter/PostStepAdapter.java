package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.PostStep;

public class PostStepAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<PostStep> postStepList;

    public PostStepAdapter(Activity activity, List<PostStep> postStepList) {
        this.activity = activity;
        this.postStepList = postStepList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_post_step, parent, false);
        PostStepViewHolder holder = new PostStepViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostStepViewHolder viewHolder = (PostStepViewHolder) holder;
        PostStep model = postStepList.get(position);
        viewHolder.tv_post_step_id.setText(Integer.toString(model.getId()));
        viewHolder.tv_post_step_detail.setText(model.getDetail());
    }

    @Override
    public int getItemCount() {
        return postStepList.size();
    }

    public class PostStepViewHolder extends RecyclerView.ViewHolder {
        TextView tv_post_step_id, tv_post_step_detail;
        public PostStepViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_post_step_id = itemView.findViewById(R.id.tvPostStepId);
            tv_post_step_detail = itemView.findViewById(R.id.tv_post_step_detail);
        }
    }
}
