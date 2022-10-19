package t2010a.cookpad_clone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.activity.PostDetailActivity;
import t2010a.cookpad_clone.adapter.SectionAdapter;
import t2010a.cookpad_clone.event.MessageEvent;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.home_client.Section;

public class HomeFragment extends Fragment {
    View itemView;
    List<Post> postList = new ArrayList<>();
    List<Section> sectionList = new ArrayList<>();
    ImageView iv_post_thumbnail_1, iv_post_thumbnail_2, iv_post_thumbnail_3, iv_post_thumbnail_4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(itemView);
        return itemView;
    }

    private void initView(View itemView) {
        Toolbar toolbar = itemView.findViewById(R.id.toolbar_home_fragment);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        initData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        SectionAdapter adapter = new SectionAdapter(getActivity(), sectionList);

        RecyclerView rv_home_tab1 = itemView.findViewById(R.id.rv_home);

        rv_home_tab1.setLayoutManager(layoutManager);
        rv_home_tab1.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            postList.add(new Post("Title " + (i + 1),
                    "http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRvC27D9KlxeEham1w-Wpd_pu3hd4A-OywxRbdnx9JFLpcTD7dfL0bD_WI6Ro8QkzrPLkBMzA9osrMpi4JSP5Y",
                    "Detail"  + (i + 1) ));
        }
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

        iv_post_thumbnail_1 = itemView.findViewById(R.id.iv_post_thumbnail1);
        iv_post_thumbnail_2 = itemView.findViewById(R.id.iv_post_thumbnail2);
        iv_post_thumbnail_3 = itemView.findViewById(R.id.iv_post_thumbnail3);
        iv_post_thumbnail_4 = itemView.findViewById(R.id.iv_post_thumbnail4);

        Glide.with(getActivity())
                .load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRvC27D9KlxeEham1w-Wpd_pu3hd4A-OywxRbdnx9JFLpcTD7dfL0bD_WI6Ro8QkzrPLkBMzA9osrMpi4JSP5Y")
                .into(iv_post_thumbnail_1);
        Glide.with(getActivity())
                .load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRvC27D9KlxeEham1w-Wpd_pu3hd4A-OywxRbdnx9JFLpcTD7dfL0bD_WI6Ro8QkzrPLkBMzA9osrMpi4JSP5Y")
                .into(iv_post_thumbnail_2);
        Glide.with(getActivity())
                .load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRvC27D9KlxeEham1w-Wpd_pu3hd4A-OywxRbdnx9JFLpcTD7dfL0bD_WI6Ro8QkzrPLkBMzA9osrMpi4JSP5Y")
                .into(iv_post_thumbnail_3);
        Glide.with(getActivity())
                .load("http://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRvC27D9KlxeEham1w-Wpd_pu3hd4A-OywxRbdnx9JFLpcTD7dfL0bD_WI6Ro8QkzrPLkBMzA9osrMpi4JSP5Y")
                .into(iv_post_thumbnail_4);
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