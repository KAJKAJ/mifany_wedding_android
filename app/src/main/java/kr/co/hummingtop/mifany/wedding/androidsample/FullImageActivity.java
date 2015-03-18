package kr.co.hummingtop.mifany.wedding.androidsample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import common.PersonalizedInfo;
import uk.co.senab.photoview.PhotoView;


public class FullImageActivity extends Activity {
    String[] imageFileNameList = PersonalizedInfo.imageFileNameList;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        init();
    }

    private void init(){
        linearLayout = (LinearLayout)findViewById(R.id.fullImageContainer);

        Bundle bundle = getIntent().getExtras();

        int position = bundle.getInt("position",0);

//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//
//        int imageId = getResources().getIdentifier(imageFileNameList[position], "drawable", getPackageName());
//        Picasso.with(getApplicationContext()).load(imageId).fit().centerInside().into(imageView);
//
////        imageView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                finish();
////            }
////        });
//
//        linearLayout.addView(imageView);

        PhotoView photoView = new PhotoView(getApplicationContext());
        photoView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        int imageId = getResources().getIdentifier(imageFileNameList[position], "drawable", getPackageName());
        Picasso.with(getApplicationContext()).load(imageId).fit().centerInside().into(photoView);

        linearLayout.addView(photoView);
    }
}
