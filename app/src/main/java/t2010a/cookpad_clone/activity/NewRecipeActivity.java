package t2010a.cookpad_clone.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    private RelativeLayout layoutUploadImg;
    private RecyclerView rvNewRecipeGradient, rvNewRecipeStep;
    private TextInputEditText etName, etOrigin, etEaterNumber, etCookingTime, etDescription;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private ImageView imageView;

    private List<PostGradient> postGradientList = new ArrayList<>();
    private NewRecipeGradientAdapter adapter;
    private List<PostStep> postStepList = new ArrayList<>();
    private NewRecipeStepAdapter adapter1;
    private User user;

    private Faker faker = new Faker();
    private Repository repository = Repository.getInstance();

    private static final String TAG = "Upload ###";
    private static int IMAGE_REQ = 1;
    private Uri imagePath;
    Map config = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        initView();

        addGradient.setOnClickListener(this);
        addStep.setOnClickListener(this);
        layoutUploadImg.setOnClickListener(this);

        setSupportActionBar(toolbar);
        appBar.setOutlineProvider(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

    }

    private void initView() {
        addGradient = findViewById(R.id.addGradient);
        addStep = findViewById(R.id.addStep);
        rvNewRecipeGradient = findViewById(R.id.rvNewRecipeGradient);
        rvNewRecipeStep = findViewById(R.id.rvNewRecipeStep);
        etName = findViewById(R.id.etName);
        etOrigin = findViewById(R.id.etOrigin);
        etEaterNumber = findViewById(R.id.etEaterNumber);
        etCookingTime = findViewById(R.id.etCookingTime);
        etDescription = findViewById(R.id.etDescription);
        toolbar = findViewById(R.id.toolbar);
        appBar = findViewById(R.id.appBar);
        imageView = findViewById(R.id.imageView);
        layoutUploadImg = findViewById(R.id.layoutUploadImg);

        user = LocalDataManager.getUserDetail();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        postGradientList = populateGradientsList();
        postStepList = populateStepList();

        adapter = new NewRecipeGradientAdapter(this, postGradientList);
        adapter1 = new NewRecipeStepAdapter(this, postStepList);

        rvNewRecipeGradient.setLayoutManager(layoutManager);
        rvNewRecipeGradient.setAdapter(adapter);

        rvNewRecipeStep.setLayoutManager(layoutManager1);
        rvNewRecipeStep.setAdapter(adapter1);

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
        String name = etName.getText().toString().toLowerCase(Locale.ROOT).trim();
        String origin = etOrigin.getText().toString().toLowerCase(Locale.ROOT).trim();
        String eaterNumber = etEaterNumber.getText().toString().toLowerCase(Locale.ROOT).trim();
        String cookingTime = etCookingTime.getText().toString().toLowerCase(Locale.ROOT).trim();
        String description = etDescription.getText().toString().toLowerCase(Locale.ROOT).trim();

        if (name.isEmpty() || origin.isEmpty() || eaterNumber.isEmpty() || cookingTime.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show();
        } else {

            if (imagePath != null) {
                MediaManager.get().upload(imagePath).callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d(TAG, "onStart: " + "started");
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Log.d(TAG, "onStart: " + "uploading");
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String strImage = (String) resultData.get("url");
                        Log.d(TAG, "onStart: " + "usuccess");

                        post.setName(name);
                        post.setOrigin(origin);
                        post.setEaterNumber(Integer.parseInt(eaterNumber));
                        post.setGradients(postGradientList);
                        post.setSteps(postStepList);
                        post.setCookingTime(Integer.parseInt(cookingTime));
                        post.setUser(user);
                        post.setDescription(description);
                        post.setThumbnails(strImage);

                        post.setStatus(1);

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
                    public void onError(String requestId, ErrorInfo error) {
                        Log.d(TAG, "onStart: " + error);
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Log.d(TAG, "onStart: " + error);
                    }
                }).dispatch();
            } else {
                Toast.makeText(this, "Thumbnail is empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setSavePost() {
        Post post = new Post();

        String name = etName.getText().toString().toLowerCase(Locale.ROOT).trim();
        String origin = etOrigin.getText().toString().toLowerCase(Locale.ROOT).trim();
        String eaterNumber = etEaterNumber.getText().toString().toLowerCase(Locale.ROOT).trim();
        String cookingTime = etCookingTime.getText().toString().toLowerCase(Locale.ROOT).trim();
        String description = etDescription.getText().toString().toLowerCase(Locale.ROOT).trim();

//        String thumbnail = imageView.toString();

        if (name.isEmpty() || origin.isEmpty() || eaterNumber.isEmpty() || cookingTime.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show();
        } else {
            post.setName(name);
            post.setOrigin(origin);
            post.setEaterNumber(Integer.parseInt(eaterNumber));
            post.setGradients(postGradientList);
            post.setSteps(postStepList);
            post.setCookingTime(Integer.parseInt(cookingTime));
            post.setUser(user);
            post.setDescription(description);
            post.setCreatedAt(LocalDateTime.now());
            post.setStatus(0);

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

            if (imagePath != null) {
                MediaManager.get().upload(imagePath).callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        Log.d(TAG, "onStart: " + "started");
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                        Log.d(TAG, "onStart: " + "uploading");
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        Log.d(TAG, "onStart: " + "usuccess");
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.d(TAG, "onStart: " + error);
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        Log.d(TAG, "onStart: " + error);
                    }
                }).dispatch();
            } else {
                Toast.makeText(this, "Thumbnail is empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<PostGradient> populateGradientsList() {
        List<PostGradient> list = new ArrayList<>();
        for (int i = 0; i < postGradientList.size(); i++) {
            PostGradient postGradient = new PostGradient();
            postGradient.setDetail(String.valueOf(i));
            list.add(postGradient);
        }
        return list;
    }

    private List<PostStep> populateStepList() {
        List<PostStep> list = new ArrayList<>();
        for (int i = 0; i < postGradientList.size(); i++) {
            PostStep postStep = new PostStep();
            postStep.setDetail(String.valueOf(i));
            list.add(postStep);
        }
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miUpload:
                setUploadPost();
                break;
            case R.id.miSave:
                setSavePost();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
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
            case R.id.layoutUploadImg:
                requestPermission();
                break;
            default:
                break;
        }
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            selectImage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, IMAGE_REQ);
        }
    }

    /*
     * sele the image from the gallery
     * */
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");// if you want to you can use pdf/gif/video
        intent.setAction(Intent.ACTION_GET_CONTENT);
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        imagePath = data.getData();
                        Picasso.get().load(imagePath).into(imageView);

                    }
                }
            });
}