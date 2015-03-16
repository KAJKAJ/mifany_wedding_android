package kr.co.hummingtop.mifany.wedding.androidsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import common.MenuInfo;
import common.PersonalizedInfo;
import common.SquaredImageView;


public class GalleryActivity extends ActionBarActivity {
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        init();
    }

    private void init(){
//        getSupportActionBar().hide();

        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageThumbnailAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FullImageActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    public class ImageThumbnailAdapter extends BaseAdapter {
        private Context context;

        public ImageThumbnailAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return imageFileNameList.length;
        }

        public Object getItem(int position) {
            return imageFileNameList[position];
        }

        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SquaredImageView imageView = (SquaredImageView) convertView;
            if (imageView == null) {
                imageView = new SquaredImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

            int imageId = getResources().getIdentifier(imageFileNameList[position], "drawable", getPackageName());
//            Picasso.with(context).load(imageId).placeholder(R.drawable.img_placeholder_1).fit().into(imageView);
            Picasso.with(context).load(imageId).fit().into(imageView);

            return imageView;
        }

        // references to our images
        String[] imageFileNameList = PersonalizedInfo.imageFileNameList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MenuInfo.MENU_SLIDESHOW, 0, MenuInfo.MENU_SLIDESHOW_TEXT);
        menu.add(0, MenuInfo.MENU_PAGER, 0, MenuInfo.MENU_PAGER_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        int id = item.getItemId();

        switch (id) {
            case MenuInfo.MENU_SLIDESHOW:
                intent = new Intent(getApplicationContext(), SlideShowActivity.class);
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