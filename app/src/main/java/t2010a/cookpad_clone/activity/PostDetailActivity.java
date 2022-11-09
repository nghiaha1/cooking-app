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

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private ImageView ivThumbnail;
    private ShapeableImageView ivUserAvatar;

    private Post post;
    private List<PostGradient> postGradientList = new ArrayList<>();
    private List<PostStep> postStepList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        initView();

        post = (Post) getIntent().getSerializableExtra("POST");
        tvPostTitle.setText(post.getName());
        tvUserFullName.setText(post.getUser().getFullName());
        tvUserUsername.setText(post.getUser().getUsername());
        tvUserAddress.setText(post.getUser().getAddress());
//        tvPostTimer.setText(post.getCookingTime());
        Glide.with(this).load(post.getThumbnails()).into(ivThumbnail);
        Glide.with(this).load(post.getUser().getAvatar()).into(ivUserAvatar);

        setRecycleView();
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
        ivThumbnail = findViewById(R.id.ivThumbnail);

        rvPostGradient = findViewById(R.id.rvPostGradient);
        rvPostStep = findViewById(R.id.rvPostStep);
    }

    private void setRecycleView() {
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
        postGradientList = post.getGradients();
        postStepList = post.getSteps();

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