package kr.co.hummingtop.mifany.wedding.androidsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import common.MenuInfo;
import common.PersonalizedInfo;


public class PagerActivity extends ActionBarActivity {
    ViewPager viewPager;

    public class ImageViewAdapter extends PagerAdapter {
        String[] imageFileNameList = PersonalizedInfo.imageFileNameList;

        @Override
        public int getCount() {
            return imageFileNameList.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            layout.setOrientation(LinearLayout.VERTICAL);

            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            int imageId = getResources().getIdentifier(imageFileNameList[position], "drawable", getPackageName());
            Picasso.with(getApplicationContext()).load(imageId).fit().centerInside().into(imageView);

            layout.addView(imageView);
            container.addView(layout);

            return layout;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        init();
    }

    private void init(){
//        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.pager);
        ImageViewAdapter adapter = new ImageViewAdapter();
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MenuInfo.MENU_GALLERY, 0, MenuInfo.MENU_GALLERY_TEXT);
        menu.add(0, MenuInfo.MENU_SLIDESHOW, 0, MenuInfo.MENU_SLIDESHOW_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case MenuInfo.MENU_GALLERY:
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case MenuInfo.MENU_SLIDESHOW:
                intent = new Intent(getApplicationContext(), SlideShowActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

