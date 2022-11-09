package t2010a.cookpad_clone.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.adapter.ShopAdapter;
import t2010a.cookpad_clone.model.shop.Product;
import t2010a.cookpad_clone.model.user.User;

public class ShoppingFragment extends Fragment {
    private View itemView;
    private SearchView searchView;
    private RecyclerView rvShop;
    private List<Product> productList = new ArrayList<>();
    private ShopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemView = inflater.inflate(R.layout.fragment_shopping, container, false);
        initView(itemView);
        setSearchView();
        return itemView;
    }

    private void initView(View itemView) {
        searchView = itemView.findViewById(R.id.searchView);
        rvShop = itemView.findViewById(R.id.rvShop);
        
        initData();

        adapter = new ShopAdapter(getActivity(), productList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        rvShop.setAdapter(adapter);
        rvShop.setLayoutManager(layoutManager);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            productList.add(new Product("Product " + ( i+ 1), BigDecimal.valueOf((i + 1) * 99999.99), "https://picsum.photos/200/300?random=" + i));
        }
//        adapter.reloadData(productList);
    }

    private void setSearchView() {
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        List<Product> filteredList = new ArrayList<>();
        for (Product item : productList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
//            else if (item.getEmail().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
//                filteredList.add(item);
//            }
        }
        Log.d("TAG", "filterList: " + filteredList.size());
        if (filteredList.isEmpty()) {
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }
}