package kr.co.hummingtop.mifany.wedding.androidsample;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import common.MenuInfo;
import common.PersonalizedInfo;


public class SlideShowActivity extends ActionBarActivity {
    MediaPlayer musicPlayer;

    Animation inAnimation;
    Animation outAnimation;

//    ArrayList<Animation> textInAnimation = new ArrayList();
//    Animation [] textInAnimation = new Animation[4];

    int textInAnimationCount = 4;
    Animation textInAnimation1;
    Animation textInAnimation2;
    Animation textInAnimation3;
    Animation textInAnimation4;

    Animation.AnimationListener textAnimationListener;

    FrameLayout container;

    LinearLayout linearLayout;
    ImageView slideShowImageView;

    TextView textView;

    Random random = new Random();
    int currentNumber = 0;
    int nextNumber = 0;

    boolean menuOpen = false;

    String[] imageFileNameList = PersonalizedInfo.imageFileNameList;
    String[] textList = PersonalizedInfo.textList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        init();
    }

    private void init(){
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //music
        musicPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music_1);
        musicPlayer.setLooping(true);
        musicPlayer.start();

        //ui
        container = (FrameLayout) findViewById(R.id.slideshow_container);

        textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);

        inAnimation = AnimationUtils.loadAnimation(this, R.anim.slideshow_in);
        outAnimation = AnimationUtils.loadAnimation(this, R.anim.slideshow_out);

        inAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText("");
                slideShowImageView.startAnimation(outAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //clear image
                if (slideShowImageView != null) {
                    linearLayout.removeView(slideShowImageView);
                    slideShowImageView = null;
                }
                if (linearLayout != null) {
                    container.removeView(linearLayout);
                    linearLayout = null;
                }

                //select next number
                selectNextNumber();

                //set new text
                changeText();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        textAnimationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setVisibility(View.INVISIBLE);
                changeImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        textInAnimation1 = AnimationUtils.loadAnimation(this, R.anim.slideshow_text_in_1);
        textInAnimation1.setAnimationListener(textAnimationListener);

        textInAnimation2 = AnimationUtils.loadAnimation(this, R.anim.slideshow_text_in_2);
        textInAnimation2.setAnimationListener(textAnimationListener);

        textInAnimation3 = AnimationUtils.loadAnimation(this, R.anim.slideshow_text_in_3);
        textInAnimation3.setAnimationListener(textAnimationListener);

        textInAnimation4 = AnimationUtils.loadAnimation(this, R.anim.slideshow_text_in_4);
        textInAnimation4.setAnimationListener(textAnimationListener);

        changeImage();
    }


    private void changeImage(){
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

    private void changeText(){
        textView.setText(textList[nextNumber]);
        textView.setVisibility(View.VISIBLE);

        int textAnimationType = random.nextInt(textInAnimationCount);
        switch(textAnimationType+1){
            case 1:
                textView.startAnimation(textInAnimation1);
                break;
            case 2:
                textView.startAnimation(textInAnimation2);
                break;
            case 3:
                textView.startAnimation(textInAnimation3);
                break;
            case 4:
                textView.startAnimation(textInAnimation4);
                break;
            default:
                textView.startAnimation(textInAnimation1);
                break;
        }
    }

    private void selectNextNumber(){
        do{
            nextNumber = random.nextInt(imageFileNameList.length);
        }while(currentNumber == nextNumber);
    }

    @Override
    protected void onPause() {
//        play = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        //상태 복원 필요
//        play = true;
//        changeImage();
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MenuInfo.MENU_SLIDESHOW, 0, MenuInfo.MENU_SLIDESHOW_TEXT);
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
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case MenuInfo.MENU_PAGER:
                intent = new Intent(getApplicationContext(), PagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        musicPlayer.stop();
        super.onDestroy();
    }
}
