package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.adapter.PostGradientAdapter;
import t2010a.cookpad_clone.adapter.PostStepAdapter;
import t2010a.cookpad_clone.adapter.SectionAdapter;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.home_client.PostGradient;
import t2010a.cookpad_clone.model.home_client.PostStep;
import t2010a.cookpad_clone.repository.Repository;

public class PostDetailActivity extends AppCompatActivity {
    TextView tv_user_name, tv_user_username, tv_user_address, tv_post_timer, tv_post_title;
    RecyclerView rv_post_gradient, rv_post_step;

    List<PostGradient> postGradientList = new ArrayList<>();
    List<PostStep> postStepList = new ArrayList<>();
    Faker faker = new Faker();

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3};
    ShapeableImageView iv_user_avatar;

    Repository postRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initBanner();
        initView();

        Post post = (Post) getIntent().getSerializableExtra("POST");
        tv_post_title.setText(post.getTitle());
    }

    private void initBanner() {
        carouselView = findViewById(R.id.carouselView);
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
    }

    private void initView() {
        iv_user_avatar = findViewById(R.id.iv_user_avatar);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_username = findViewById(R.id.tv_user_username);
        tv_user_address = findViewById(R.id.tv_user_address);
        tv_post_timer = findViewById(R.id.tv_post_timer);
        tv_post_title = findViewById(R.id.tv_post_title);

        rv_post_gradient = findViewById(R.id.rv_post_gradient);
        rv_post_step = findViewById(R.id.rv_post_step);

        initData();

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        PostGradientAdapter adapter1 = new PostGradientAdapter(this, postGradientList);
        PostStepAdapter adapter2 = new PostStepAdapter(this, postStepList);

        rv_post_gradient.setLayoutManager(layoutManager1);
        rv_post_gradient.setAdapter(adapter1);

        rv_post_step.setLayoutManager(layoutManager2);
        rv_post_step.setAdapter(adapter2);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            postGradientList.add(new PostGradient(i + 1,
                    faker.food.spice()));
        }

        for (int i = 0; i < 10; i++) {
            postStepList.add(new PostStep(i + 1,
                    faker.lorem.paragraph(3)));
        }
    }

}