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
    NewRecipeGradientAdapter adapter = new NewRecipeGradientAdapter();

    List<PostGradient> postGradientList = new ArrayList<>();
    List<PostStep> postStepList = new ArrayList<>();

    Faker faker = new Faker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        initView();

        addGradient.setOnClickListener(this);
    }

    private void initView() {
        addGradient = findViewById(R.id.addGradient);
        addStep = findViewById(R.id.addStep);
        rv_new_recipe_gradient = findViewById(R.id.rv_new_recipe_gradient);
        rv_new_recipe_step = findViewById(R.id.rv_new_recipe_step);

        initData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        NewRecipeGradientAdapter adapter = new NewRecipeGradientAdapter(this, postGradientList);
        NewRecipeStepAdapter adapter1 = new NewRecipeStepAdapter(this, postStepList);

        rv_new_recipe_gradient.setLayoutManager(layoutManager);
        rv_new_recipe_gradient.setAdapter(adapter);

        rv_new_recipe_step.setLayoutManager(layoutManager1);
        rv_new_recipe_step.setAdapter(adapter1);
    }

    private void initData() {
        postGradientList.add(new PostGradient(1, ""));
        postGradientList.add(new PostGradient(2, ""));
        postGradientList.add(new PostGradient(3, ""));

        for (int i = 4; i < postGradientList.size(); i++) {
            postGradientList.add(new PostGradient(i + 1,
                    ""));
        }
        for (int i = 0; i < postStepList.size(); i++) {
            postStepList.add(new PostStep(i + 1,
                    ""));
        }
        postStepList.size();
        postGradientList.size();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addGradient:
                postGradientList.add(new PostGradient());
                Toast.makeText(this, "aaaaaa", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "size: " + postGradientList.size());
                adapter.notifyItemInserted(postGradientList.size());
                adapter.notifyDataSetChanged();
                break;
            case R.id.addStep:
                break;
        }
    }
}