package kr.co.hummingtop.mifany.wedding.androidsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;


public class MainActivity extends Activity {
    public class ImageViewAdapter extends PagerAdapter {
        String[] imageList = {
                "img_01",
                "img_02",
                "img_03",
                "img_04",
                "img_05",
                "img_06",
                "img_07",
                "img_08",
                "img_09",
                "img_10",
                "img_11",
                "img_12",
                "img_13",
                "img_14",
                "img_15",
                "img_16",
                "img_17",
                "img_18",
                "img_19",
                "img_20",
                "img_21"
        };

        @Override
        public int getCount() {
            return imageList.length;
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

            int imageId = getResources().getIdentifier(imageList[position], "drawable", getPackageName());
            Picasso.with(getApplicationContext()).load(imageId).fit().centerInside().into(imageView);

            layout.addView(imageView);
            container.addView(layout);

            return layout;
        }

    }

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        viewPager = (ViewPager) findViewById(R.id.pager);
        ImageViewAdapter adapter = new ImageViewAdapter();
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

