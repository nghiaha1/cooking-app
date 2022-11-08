package t2010a.cookpad_clone.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.adapter.SearchAdapter;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;

public class SearchFragment extends Fragment {
    private View itemView;
    private RecyclerView rvSearch;
    private List<User> userList = new ArrayList<>();
    private Repository repository = Repository.getInstance();
    private SearchAdapter adapter;
    private SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_search, container, false);
        initView(itemView);
        setSearchView();
        return itemView;
    }

    private void initView(View itemView) {
        searchView = itemView.findViewById(R.id.searchView);


        initData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        adapter = new SearchAdapter(getActivity(), userList);

        rvSearch = itemView.findViewById(R.id.rvSearch);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvSearch.addItemDecoration(itemDecoration);

        rvSearch.setAdapter(adapter);
        rvSearch.setLayoutManager(layoutManager);
    }

    private void initData() {
        repository.getService().getUserList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code()==200){
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    userList = response.body();
                    adapter.reloadData(userList);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
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
        List<User> filteredList = new ArrayList<>();
        for (User user : userList) {
            if (user.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(user);
            }
        }
        Log.d("TAG", "filterList: " + filteredList.size());

        if (filteredList.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }
}