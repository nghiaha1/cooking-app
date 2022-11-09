package t2010a.cookpad_clone.fragment;

import android.content.Intent;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.PostDetailActivity;
import t2010a.cookpad_clone.adapter.SearchAdapter;
import t2010a.cookpad_clone.event.MessageEvent;
import t2010a.cookpad_clone.model.home_client.HomeModel;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;

public class SearchFragment extends Fragment {
    private View itemView;
    private RecyclerView rvSearch;
    private List<Post> postList = new ArrayList<>();
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
        rvSearch = itemView.findViewById(R.id.rvSearch);

        initData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        adapter = new SearchAdapter(getActivity(), postList);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvSearch.addItemDecoration(itemDecoration);

        rvSearch.setAdapter(adapter);
        rvSearch.setLayoutManager(layoutManager);
    }

    private void initData() {
        repository.getService().getPostList().enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                if (response.code() == 200) {
                    postList = response.body().getContent();
                    adapter.reloadData(postList);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {

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
        List<Post> filteredList = new ArrayList<>();
        for (Post item : postList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            } else if (item.getUser().getFullName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(item);
            }
        }
        Log.d("TAG", "filterList: " + filteredList.size());

        if (filteredList.isEmpty()) {
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(filteredList);
        }
    }

    private void toPostDetail(Post post) {
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        Log.d("TAG", "toPostDetail: ");
        intent.putExtra("POST", post);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.PostEvent movieEvent) {
        Post post = movieEvent.getPost();
        toPostDetail(post);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}