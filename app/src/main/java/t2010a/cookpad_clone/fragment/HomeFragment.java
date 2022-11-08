package t2010a.cookpad_clone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
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
    private List<Section> sectionList = new ArrayList<>();

    private Repository repository = Repository.getInstance();
    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3};

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
                Log.d("TAG", "onClick: "+position);
            }
        });

        initData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        SectionAdapter adapter = new SectionAdapter(getActivity(), sectionList);

        RecyclerView rvHomeTab1 = itemView.findViewById(R.id.rvHome);

        rvHomeTab1.setLayoutManager(layoutManager);
        rvHomeTab1.setAdapter(adapter);
    }

    private void initData() {
        initSection("Bạn đang thèm món gì?",
                "Không chắc? Tiếp tục tạo bất ngờ",
                postList);
        initSection("Du lịch vòng quanh thế giới từ bếp",
                "Ẩm thực Ý không chỉ có mì Ý! Xem ngay các đầu bếp tại gia trên thế giới đang nấu món gì hôm nay nhé!",
                postList);
        initSection("Tré trộn lộn xộn mà ngon",
                "Món ăn xế mới nổi gần đây! Nào trứng cút, nem, chả, xoài hoặc cóc thập cẩm đủ thứ, quan trọng là cái nước trộn nè, công thức đây nha",
                postList);
        initSection("Khám phá xem thứ gì đang trong mùa nào!",
                "Không chắc? Tiếp tục tạo bất ngờ",
                postList);

        repository.getService().getPostList().enqueue(new Callback<BaseResponse<List<Post>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Post>>>call, Response<BaseResponse<List<Post>>> response) {
                if (response.code() == 200) {
                    postList = (List<Post>) response.body().getContent();
                    Log.d("TAG", "onResponse: " + postList);
                } else {
                    Log.d("TAG", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Post>>> call, Throwable t) {

            }
        });

        for (int i = 0; i < postList.size(); i++) {
            Post post = new Post();
            post = postList.get(i);
        }
    }

    private void initSection(String title,String note, List<Post> posts) {
        Section section = new Section(title,note, posts);
        sectionList.add(section);
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