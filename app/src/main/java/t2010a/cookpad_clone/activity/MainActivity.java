package t2010a.cookpad_clone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.fragment.HomeFragment;
import t2010a.cookpad_clone.fragment.NewRecipeFragment;
import t2010a.cookpad_clone.fragment.ProfileFragment;
import t2010a.cookpad_clone.fragment.SearchFragment;
import t2010a.cookpad_clone.fragment.ShoppingFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    NewRecipeFragment newRecipeFragment;
    ShoppingFragment shoppingFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

        addFragments(searchFragment);
        addFragments(newRecipeFragment);
        addFragments(shoppingFragment);
        addFragments(profileFragment);
        addFragments(homeFragment);
    }

    private void initView() {
        navigationView = findViewById(R.id.bottom_nav);

        searchFragment = new SearchFragment();
        newRecipeFragment = new NewRecipeFragment();
        shoppingFragment = new ShoppingFragment();
        profileFragment = new ProfileFragment();
        homeFragment = new HomeFragment();
    }

    private void addFragments(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    private void onActionHome() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(homeFragment);
        transaction.hide(searchFragment);
        transaction.hide(newRecipeFragment);
        transaction.hide(shoppingFragment);
        transaction.hide(profileFragment);
        transaction.commit();
    }

    private void onActionSearch() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(searchFragment);
        transaction.hide(homeFragment);
        transaction.hide(newRecipeFragment);
        transaction.hide(shoppingFragment);
        transaction.hide(profileFragment);
        transaction.commit();
    }

    private void onActionNewRecipe() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(newRecipeFragment);
        transaction.hide(homeFragment);
        transaction.hide(searchFragment);
        transaction.hide(shoppingFragment);
        transaction.hide(profileFragment);
        transaction.commit();
    }

    private void onActionShopping() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(shoppingFragment);
        transaction.hide(homeFragment);
        transaction.hide(newRecipeFragment);
        transaction.hide(searchFragment);
        transaction.hide(profileFragment);
        transaction.commit();
    }

    private void onActionProfile() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(profileFragment);
        transaction.hide(homeFragment);
        transaction.hide(newRecipeFragment);
        transaction.hide(searchFragment);
        transaction.hide(shoppingFragment);
        transaction.commit();
    }

    private void initListener() {
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        onActionHome();
                        break;
                    case R.id.action_search:
                        onActionSearch();
                        break;
                    case R.id.action_new_recipe:
                        onActionNewRecipe();
                        break;
                    case R.id.action_shopping:
                        onActionShopping();
                        break;
                    case R.id.action_profile:
                        onActionProfile();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}