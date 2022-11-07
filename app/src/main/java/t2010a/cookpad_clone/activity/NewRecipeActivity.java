package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;
import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.adapter.NewRecipeGradientAdapter;
import t2010a.cookpad_clone.adapter.NewRecipeStepAdapter;
import t2010a.cookpad_clone.model.home_client.PostGradient;
import t2010a.cookpad_clone.model.home_client.PostStep;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout addGradient, addStep;
    private RecyclerView rvNewRecipeGradient, rvNewRecipeStep;
    private TextView tv_post_step_id;

    private List<PostGradient> postGradientList = new ArrayList<>();
    private NewRecipeGradientAdapter adapter;
    private List<PostStep> postStepList = new ArrayList<>();
    private NewRecipeStepAdapter adapter1;


    Faker faker = new Faker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        initView();

        addGradient.setOnClickListener(this);
        addStep.setOnClickListener(this);
    }

    private void initView() {
        addGradient = findViewById(R.id.addGradient);
        addStep = findViewById(R.id.addStep);
        rvNewRecipeGradient = findViewById(R.id.rvNewRecipeGradient);
        rvNewRecipeStep = findViewById(R.id.rvNewRecipeStep);

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
            default:
                break;
        }
    }
}