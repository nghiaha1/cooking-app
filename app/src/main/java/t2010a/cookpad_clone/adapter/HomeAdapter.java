package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.Post;

public class HomeAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Post> postList;

    public HomeAdapter(Activity activity, List<Post> postList) {
        this.activity = activity;
        this.postList = postList;
    }

    public void reloadData(List<Post> list) {
        postList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_home, parent, false);
        HomeViewHolder holder = new HomeViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeViewHolder viewHolder = (HomeViewHolder) holder;
        Post model = postList.get(position);
        Glide.with(activity).load(model.getUser().getAvatar()).into(viewHolder.ivAvatar);
        viewHolder.tvFullName.setText(model.getUser().getFullName());
        viewHolder.tvEmail.setText(model.getUser().getEmail());
        Glide.with(activity).load(model.getThumbnails()).into(viewHolder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        if (postList != null) {
            return postList.size();
        }
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivAvatar;
        TextView tvFullName, tvEmail;
        ImageView ivThumbnail;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
