package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.event.MessageEvent;
import t2010a.cookpad_clone.model.shop.Product;

public class ShopAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Product> productList;

    public ShopAdapter(Activity activity, List<Product> productList) {
        this.activity = activity;
        this.productList = productList;
    }

    public void reloadData(List<Product> list) {
        productList = list;
        notifyDataSetChanged();
    }

    public void setFilteredList(List<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_shop, parent, false);
        ShopViewHolder holder = new ShopViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShopViewHolder viewHolder = (ShopViewHolder) holder;
        Product model = productList.get(position);
        Glide.with(activity).load(model.getThumbnails()).into(viewHolder.ivThumbnail);
        viewHolder.tvName.setText(model.getName());
        viewHolder.tvPrice.setText(model.getPrice().toString() + " ₫");

    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        CheckBox cbFavourite;
        TextView tvName, tvPrice;
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            cbFavourite = itemView.findViewById(R.id.cbFavourite);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);

            if (cbFavourite.isChecked()) {

            }
        }
    }
}
