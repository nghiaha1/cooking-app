package t2010a.cookpad_clone.activity;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.adapter.PostGradientAdapter;
import t2010a.cookpad_clone.adapter.PostStepAdapter;
import t2010a.cookpad_clone.model.client_model.Content;
import t2010a.cookpad_clone.model.client_model.Ingredient;
import t2010a.cookpad_clone.model.client_model.Making;

public class PostDetailActivity extends AppCompatActivity {
    private TextView tvUserFullName, tvUserUsername, tvUserAddress, tvPostTimer, tvPostTitle;
    private RecyclerView rvPostGradient, rvPostStep;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private ImageView ivThumbnail;
    private ShapeableImageView ivUserAvatar;

    private Content post;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<Making> makingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        initView();

        post = (Content) getIntent().getSerializableExtra("POST");
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
        tvPostTitle = findViewById(R.id.tvFullName);
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

        PostGradientAdapter adapter1 = new PostGradientAdapter(this, ingredientList);
        PostStepAdapter adapter2 = new PostStepAdapter(this, makingList);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvPostGradient.addItemDecoration(itemDecoration);
        rvPostStep.addItemDecoration(itemDecoration);


        rvPostGradient.setLayoutManager(layoutManager1);
        rvPostGradient.setAdapter(adapter1);

        rvPostStep.setLayoutManager(layoutManager2);
        rvPostStep.setAdapter(adapter2);
    }

    private void initData() {
        ingredientList = post.getIngredient();
        makingList = post.getMaking();

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