package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
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
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.home_client.PostGradient;
import t2010a.cookpad_clone.model.home_client.PostStep;
import t2010a.cookpad_clone.repository.Repository;

public class PostDetailActivity extends AppCompatActivity {
    private TextView tvUserFullName, tvUserUsername, tvUserAddress, tvPostTimer, tvPostTitle;
    private RecyclerView rvPostGradient, rvPostStep;
    private Toolbar toolbar;
    private AppBarLayout appBar;

    private List<PostGradient> postGradientList = new ArrayList<>();
    private List<PostStep> postStepList = new ArrayList<>();
    private Faker faker = new Faker();

    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3};
    private ShapeableImageView ivUserAvatar;

    Repository postRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initBanner();
        initView();

        Post post = (Post) getIntent().getSerializableExtra("POST");
        tvPostTitle.setText(post.getName());
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
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        tvUserFullName = findViewById(R.id.tvUserFullName);
        tvUserUsername = findViewById(R.id.tvUserUsername);
        tvUserAddress = findViewById(R.id.tvUserAddress);
        tvPostTimer = findViewById(R.id.tvPostTimer);
        tvPostTitle = findViewById(R.id.tvPostTitle);
        toolbar = findViewById(R.id.toolbar);
        appBar = findViewById(R.id.appBar);

        rvPostGradient = findViewById(R.id.rvPostGradient);
        rvPostStep = findViewById(R.id.rvPostStep);

        initData();

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        PostGradientAdapter adapter1 = new PostGradientAdapter(this, postGradientList);
        PostStepAdapter adapter2 = new PostStepAdapter(this, postStepList);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvPostGradient.addItemDecoration(itemDecoration);
        rvPostStep.addItemDecoration(itemDecoration);


        rvPostGradient.setLayoutManager(layoutManager1);
        rvPostGradient.setAdapter(adapter1);

        rvPostStep.setLayoutManager(layoutManager2);
        rvPostStep.setAdapter(adapter2);
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

        setSupportActionBar(toolbar);
        appBar.setOutlineProvider(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}