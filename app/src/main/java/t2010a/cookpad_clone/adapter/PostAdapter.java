package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.Post;

public class PostAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Post> postList;
    private String section;

    public PostAdapter(Activity activity, List<Post> postList) {
        this.activity = activity;
        this.postList = postList;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_post, parent, false);
        PostViewHolder holder = new PostViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostViewHolder viewHolder = (PostViewHolder) holder;
        Post model = postList.get(position);
        Glide.with(activity).load(model.getThumbnail()).into(viewHolder.iv_post_thumbnail_1);
        Glide.with(activity).load(model.getThumbnail()).into(viewHolder.iv_post_thumbnail_2);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_post_thumbnail_1, iv_post_thumbnail_2;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_post_thumbnail_1 = itemView.findViewById(R.id.iv_post_thumbnail_1);
            iv_post_thumbnail_2 = itemView.findViewById(R.id.iv_post_thumbnail_2);

        }
    }
}
