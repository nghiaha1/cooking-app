package t2010a.cookpad_clone.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.Post;

public class PostDetailActivity extends AppCompatActivity {
    TextView tv_user_name, tv_user_username, tv_user_address, tv_post_timer, tv_post_title;
    TextView tv_post_ingredient_1, tv_post_ingredient_2, tv_post_ingredient_3,
            tv_post_ingredient_4, tv_post_ingredient_5, tv_post_ingredient_6,
            tv_post_ingredient_7, tv_post_ingredient_8, tv_post_ingredient_9,
            tv_post_ingredient_10;
    TextView tv_post_step_1_context, tv_post_step_2_context, tv_post_step_3_context,
            tv_post_step_4_context, tv_post_step_5_context, tv_post_step_6_context,
            tv_post_step_7_context, tv_post_step_8_context, tv_post_step_9_context,
            tv_post_step_10_context;
    LinearLayout layout_post_ingredient_1, layout_post_ingredient_2, layout_post_ingredient_3,
            layout_post_ingredient_4, layout_post_ingredient_5, layout_post_ingredient_6,
            layout_post_ingredient_7, layout_post_ingredient_8, layout_post_ingredient_9,
            layout_post_ingredient_10;
    LinearLayout layout_post_step_1, layout_post_step_2, layout_post_step_3,
            layout_post_step_4, layout_post_step_5, layout_post_step_6,
            layout_post_step_7, layout_post_step_8, layout_post_step_9,
            layout_post_step_10;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3};
    ShapeableImageView iv_user_avatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initBanner();
        initView();
        showHideLayout();

        Post post = (Post) getIntent().getSerializableExtra("POST");
        tv_post_title.setText(post.getTitle());
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
        iv_user_avatar = findViewById(R.id.iv_user_avatar);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_username = findViewById(R.id.tv_user_username);
        tv_user_address = findViewById(R.id.tv_user_address);
        tv_post_timer = findViewById(R.id.tv_post_timer);
        tv_post_title = findViewById(R.id.tv_post_title);

        tv_post_ingredient_1 = findViewById(R.id.tv_post_ingredient_1);
        tv_post_ingredient_2 = findViewById(R.id.tv_post_ingredient_2);
        tv_post_ingredient_3 = findViewById(R.id.tv_post_ingredient_3);
        tv_post_ingredient_4 = findViewById(R.id.tv_post_ingredient_4);
        tv_post_ingredient_5 = findViewById(R.id.tv_post_ingredient_5);
        tv_post_ingredient_6 = findViewById(R.id.tv_post_ingredient_6);
        tv_post_ingredient_7 = findViewById(R.id.tv_post_ingredient_7);
        tv_post_ingredient_8 = findViewById(R.id.tv_post_ingredient_8);
        tv_post_ingredient_9 = findViewById(R.id.tv_post_ingredient_9);
        tv_post_ingredient_10 = findViewById(R.id.tv_post_ingredient_10);

        layout_post_ingredient_1 = findViewById(R.id.layout_post_ingredient_1);
        layout_post_ingredient_2 = findViewById(R.id.layout_post_ingredient_2);
        layout_post_ingredient_3 = findViewById(R.id.layout_post_ingredient_3);
        layout_post_ingredient_4 = findViewById(R.id.layout_post_ingredient_4);
        layout_post_ingredient_5 = findViewById(R.id.layout_post_ingredient_5);
        layout_post_ingredient_6 = findViewById(R.id.layout_post_ingredient_6);
        layout_post_ingredient_7 = findViewById(R.id.layout_post_ingredient_7);
        layout_post_ingredient_8 = findViewById(R.id.layout_post_ingredient_8);
        layout_post_ingredient_9 = findViewById(R.id.layout_post_ingredient_9);
        layout_post_ingredient_10 = findViewById(R.id.layout_post_ingredient_10);

        tv_post_step_1_context = findViewById(R.id.tv_post_step_1_context);
        tv_post_step_2_context = findViewById(R.id.tv_post_step_2_context);
        tv_post_step_3_context = findViewById(R.id.tv_post_step_3_context);
        tv_post_step_4_context = findViewById(R.id.tv_post_step_4_context);
        tv_post_step_5_context = findViewById(R.id.tv_post_step_5_context);
        tv_post_step_6_context = findViewById(R.id.tv_post_step_6_context);
        tv_post_step_7_context = findViewById(R.id.tv_post_step_7_context);
        tv_post_step_8_context = findViewById(R.id.tv_post_step_8_context);
        tv_post_step_9_context = findViewById(R.id.tv_post_step_9_context);
        tv_post_step_10_context = findViewById(R.id.tv_post_step_10_context);

        layout_post_step_1 = findViewById(R.id.layout_post_step_1);
        layout_post_step_2 = findViewById(R.id.layout_post_step_2);
        layout_post_step_3 = findViewById(R.id.layout_post_step_3);
        layout_post_step_4 = findViewById(R.id.layout_post_step_4);
        layout_post_step_5 = findViewById(R.id.layout_post_step_5);
        layout_post_step_6 = findViewById(R.id.layout_post_step_6);
        layout_post_step_7 = findViewById(R.id.layout_post_step_7);
        layout_post_step_8 = findViewById(R.id.layout_post_step_8);
        layout_post_step_9 = findViewById(R.id.layout_post_step_9);
        layout_post_step_10 = findViewById(R.id.layout_post_step_10);
    }

    private void showHideLayout() {
//        layout_post_ingredient_5.setVisibility(View.GONE);
//        layout_post_ingredient_6.setVisibility(View.GONE);
//        layout_post_ingredient_7.setVisibility(View.GONE);
//        layout_post_ingredient_8.setVisibility(View.GONE);
//        layout_post_ingredient_9.setVisibility(View.GONE);
//        layout_post_ingredient_10.setVisibility(View.GONE);
//
//        layout_post_step_5.setVisibility(View.GONE);
//        layout_post_step_6.setVisibility(View.GONE);
//        layout_post_step_7.setVisibility(View.GONE);
//        layout_post_step_8.setVisibility(View.GONE);
//        layout_post_step_9.setVisibility(View.GONE);
//        layout_post_step_10.setVisibility(View.GONE);
    }
}