package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

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
    LinearLayout addGradient, addStep;
    RecyclerView rv_new_recipe_gradient, rv_new_recipe_step;

    List<PostGradient> postGradientList = new ArrayList<>();
    NewRecipeGradientAdapter adapter;
    List<PostStep> postStepList = new ArrayList<>();
    NewRecipeStepAdapter adapter1;


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
        rv_new_recipe_gradient = findViewById(R.id.rv_new_recipe_gradient);
        rv_new_recipe_step = findViewById(R.id.rv_new_recipe_step);

        initData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        adapter = new NewRecipeGradientAdapter(this, postGradientList);
        adapter1 = new NewRecipeStepAdapter(this, postStepList);

        rv_new_recipe_gradient.setLayoutManager(layoutManager);
        rv_new_recipe_gradient.setAdapter(adapter);

        rv_new_recipe_step.setLayoutManager(layoutManager1);
        rv_new_recipe_step.setAdapter(adapter1);

    }

    private void initData() {
        for (int i = 4; i < postGradientList.size(); i++) {
            postGradientList.add(new PostGradient(i + 1,
                    ""));
        }
        for (int i = 0; i < postStepList.size(); i++) {
            postStepList.add(new PostStep(i + 1,
                    ""));
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