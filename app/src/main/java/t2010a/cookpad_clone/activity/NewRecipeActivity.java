package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.bloco.faker.Faker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.adapter.NewRecipeGradientAdapter;
import t2010a.cookpad_clone.adapter.NewRecipeStepAdapter;
import t2010a.cookpad_clone.local_data.LocalDataManager;
import t2010a.cookpad_clone.model.home_client.Post;
import t2010a.cookpad_clone.model.home_client.PostGradient;
import t2010a.cookpad_clone.model.home_client.PostStep;
import t2010a.cookpad_clone.model.user.User;
import t2010a.cookpad_clone.repository.Repository;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout addGradient, addStep;
    private RecyclerView rvNewRecipeGradient, rvNewRecipeStep;
    private TextView tvPostStepId;
    private Button uploadPost, savePost;
    private TextInputEditText etName, etOrigin, etEaterNumber,
            etGradientDetail, etStepDetail, etCookingTime;

    private List<PostGradient> postGradientList = new ArrayList<>();
    private NewRecipeGradientAdapter adapter;
    private List<PostStep> postStepList = new ArrayList<>();
    private NewRecipeStepAdapter adapter1;
    private User user;

    private Faker faker = new Faker();
    private Repository repository = Repository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        initView();

        addGradient.setOnClickListener(this);
        addStep.setOnClickListener(this);
        uploadPost.setOnClickListener(this);
    }

    private void initView() {
        addGradient = findViewById(R.id.addGradient);
        addStep = findViewById(R.id.addStep);
        rvNewRecipeGradient = findViewById(R.id.rvNewRecipeGradient);
        rvNewRecipeStep = findViewById(R.id.rvNewRecipeStep);
        etName = findViewById(R.id.etName);
        etOrigin = findViewById(R.id.etOrigin);
        etEaterNumber = findViewById(R.id.etEaterNumber);
        etGradientDetail = findViewById(R.id.etGradientDetail);
        etStepDetail = findViewById(R.id.etStepDetail);
        etCookingTime = findViewById(R.id.etCookingTime);
        uploadPost = findViewById(R.id.uploadPost);

        user = LocalDataManager.getUserDetail();

        initData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        adapter = new NewRecipeGradientAdapter(this, postGradientList);
        adapter1 = new NewRecipeStepAdapter(this, postStepList);

        rvNewRecipeGradient.setLayoutManager(layoutManager);
        rvNewRecipeGradient.setAdapter(adapter);

        rvNewRecipeStep.setLayoutManager(layoutManager1);
        rvNewRecipeStep.setAdapter(adapter1);

    }

    private void initData() {
        for (int i = 4; i < postGradientList.size(); i++) {
            postGradientList.add(new PostGradient(i + 1, ""));
        }
        for (int i = 0; i < postStepList.size(); i++) {
            postStepList.add(new PostStep(i + 1, ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setAddGradient(PostGradient postGradient) {
        postGradientList.add(postGradient);
        adapter.notifyItemInserted(postGradientList.size() + 1);
        for (int i = 0; i < postGradientList.size(); i++) {
            Log.d("TAG", "Id " + i + " " + postGradientList.get(i).getId());
        }
    }

    private void setAddStep(PostStep postStep) {
        postStepList.add(postStep);
        adapter1.notifyItemInserted(postStepList.size() + 1);
        for (int i = 0; i < postStepList.size(); i++) {
            Log.d("TAG", "Id " + i + " " + postStepList.get(i).getId());
        }
    }

    private void setUploadPost() {
        Post post = new Post();
        PostGradient postGradient = new PostGradient();
        PostStep postStep = new PostStep();

        String name = etName.getText().toString().toLowerCase(Locale.ROOT).trim();
        String origin = etOrigin.getText().toString().toLowerCase(Locale.ROOT).trim();
        String eaterNumber = etEaterNumber.getText().toString().toLowerCase(Locale.ROOT).trim();
        String gradientDetail = etGradientDetail.getText().toString().toLowerCase(Locale.ROOT).trim();
        String stepDetail = etStepDetail.getText().toString().toLowerCase(Locale.ROOT).trim();
        String cookingTime = etCookingTime.getText().toString().toLowerCase(Locale.ROOT).trim();

        postGradient.setDetail(gradientDetail);
        postStep.setDetail(stepDetail);

        postGradientList.add(postGradient);
        postStepList.add(postStep);

        post.setName(name);
        post.setOrigin(origin);
        post.setEaterNumber(Integer.parseInt(eaterNumber));
        post.setGradients(postGradientList);
        post.setSteps(postStepList);
        post.setCookingTime(Integer.parseInt(cookingTime));
        post.setUser(user);

        repository.getService().createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.code() == 200) {
                    Toast.makeText(NewRecipeActivity.this, "Success to upload new post", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NewRecipeActivity.this, "Fail to upload new post", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addGradient:
                setAddGradient(new PostGradient());
                adapter.reloadData(postGradientList);
                break;

            case R.id.addStep:
                setAddStep(new PostStep());
                adapter1.reloadData(postStepList);
                break;

            case R.id.uploadPost:
                setUploadPost();
                break;

            default:
                break;
        }
    }
}