package t2010a.cookpad_clone.adapter;

import android.annotation.SuppressLint;
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
import t2010a.cookpad_clone.model.user.User;

public class SearchAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<User> userList;

    public SearchAdapter(Activity activity, List<User> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    public void reloadData(List<User> list) {
        userList = list;
        notifyDataSetChanged();
    }

    public void setFilteredList(List<User> filteredList) {
        userList = filteredList;
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
        User model = userList.get(position);
        viewHolder.tvFullName.setText(model.getFullName());
        viewHolder.tvEmail.setText(model.getEmail());
        Glide.with(activity).load(model.getAvatar()).into(viewHolder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivAvatar;
        TextView tvFullName, tvEmail;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
