package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import t2010a.cookpad_clone.R;

public class NewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
    }

    private void initView() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}