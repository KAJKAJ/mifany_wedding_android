package kr.co.hummingtop.mifany.wedding.androidsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.Random;

import common.MenuInfo;
import common.PersonalizedInfo;


public class SlideShowActivity extends ActionBarActivity {
    Animation inAnimation;

    FrameLayout container;

    LinearLayout linearLayout;
    ImageView slideShowImageView;

    Random random = new Random();
    int currentNumber = 0;

    String[] imageFileNameList = PersonalizedInfo.imageFileNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        init();
    }

    private void init(){
        getSupportActionBar().hide();
        container = (FrameLayout) findViewById(R.id.slideshow_container);

        inAnimation = AnimationUtils.loadAnimation(this, R.anim.slideshow_in);
        inAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                changeImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        changeImage();
    }

    private void changeImage(){
        int nextNumber;

        do{
            nextNumber = random.nextInt(imageFileNameList.length);
        }while(nextNumber == currentNumber);

        if(slideShowImageView != null){
            linearLayout.removeView(slideShowImageView);
            slideShowImageView = null;
        }
        if(linearLayout != null){
            container.removeView(linearLayout);
            linearLayout = null;
        }

        linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        slideShowImageView = new ImageView(getApplicationContext());
        slideShowImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        int imageId = getResources().getIdentifier(imageFileNameList[nextNumber], "drawable", getPackageName());
        Picasso.with(getApplicationContext()).load(imageId).fit().centerInside().into(slideShowImageView);

        linearLayout.addView(slideShowImageView);
        container.addView(linearLayout);

        currentNumber = nextNumber;
        slideShowImageView.startAnimation(inAnimation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MenuInfo.MENU_GALLERY, 0, MenuInfo.MENU_GALLERY_TEXT);
        menu.add(0, MenuInfo.MENU_PAGER, 0, MenuInfo.MENU_PAGER_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        int id = item.getItemId();

        switch (id) {
            case MenuInfo.MENU_GALLERY:
                intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(intent);
                break;
            case MenuInfo.MENU_PAGER:
                intent = new Intent(getApplicationContext(), PagerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
