package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.user.User;

public class SearchAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Post> postList;

    public SearchAdapter(Activity activity, List<Post> postList) {
        this.activity = activity;
        this.postList = postList;
    }

    public void reloadData(List<Post> list) {
        postList = list;
        notifyDataSetChanged();
    }

    public void setFilteredList(List<Post> filteredList) {
        postList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_search, parent, false);
        SearchViewHolder holder = new SearchViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder viewHolder = (SearchViewHolder) holder;
        Post model = postList.get(position);
        viewHolder.tvPostTitle.setText(model.getName());
        viewHolder.tvPostDescription.setText(model.getDescription());
        Glide.with(activity).load(model.getThumbnails()).into(viewHolder.ivPostThumbnail);
    }

    @Override
    public int getItemCount() {
        if (postList != null) {
            return postList.size();
        }
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivPostThumbnail;
        TextView tvPostTitle, tvPostDescription;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPostThumbnail = itemView.findViewById(R.id.ivPostThumbnail);
            tvPostTitle = itemView.findViewById(R.id.tvPostTitle);
            tvPostDescription = itemView.findViewById(R.id.tvPostDescription);
        }
    }
}
