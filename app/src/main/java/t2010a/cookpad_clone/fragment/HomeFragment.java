package t2010a.cookpad_clone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.PostDetailActivity;
import t2010a.cookpad_clone.adapter.HomeAdapter;
import t2010a.cookpad_clone.adapter.SearchAdapter;
import t2010a.cookpad_clone.adapter.SectionAdapter;
import t2010a.cookpad_clone.event.MessageEvent;
import t2010a.cookpad_clone.model.home_client.BaseResponse;
import t2010a.cookpad_clone.model.home_client.HomeModel;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.home_client.Section;
import t2010a.cookpad_clone.repository.Repository;

public class HomeFragment extends Fragment {
    private View itemView;
    private List<Post> postList = new ArrayList<>();
    private Repository repository = Repository.getInstance();
    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3};
    private HomeAdapter adapter;
    private RecyclerView rvHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(itemView);
        return itemView;
    }

    private void initView(View itemView) {
        carouselView = itemView.findViewById(R.id.homeCarouselView);
        rvHome = itemView.findViewById(R.id.rvHome);

        initBanner();
        initData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        adapter = new HomeAdapter(getActivity(), postList);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvHome.addItemDecoration(itemDecoration);

        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(layoutManager);
    }

    private void initBanner() {
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Log.d("TAG", "onClick: " + position);
            }
        });
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

    private void toPostDetail(Post post) {
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        intent.putExtra("POST", post);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MovieEvent movieEvent) {
        Post post = movieEvent.getMovie();
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