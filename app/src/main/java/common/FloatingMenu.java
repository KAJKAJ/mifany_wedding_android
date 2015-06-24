package common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import kr.co.hummingtop.mifany.wedding.androidsample.GalleryActivity;
import kr.co.hummingtop.mifany.wedding.androidsample.MovieActivity;
import kr.co.hummingtop.mifany.wedding.androidsample.PagerActivity;
import kr.co.hummingtop.mifany.wedding.androidsample.R;
import kr.co.hummingtop.mifany.wedding.androidsample.SlideShowActivity;

/**
 * Created by realjangsun on 15. 6. 24..
 */
public class FloatingMenu {
    public static final int MENU_TAG = 0;

    private Activity target = null;
    private View.OnClickListener menuClickedHandler = null;

    public FloatingMenu(Activity target){
        this.target = target;
        init();
    };

    private void init(){
        //create menu icon
        ImageView iconMenu = new ImageView(this.target); // Create an icon
        Drawable iconMenuDrawable = target.getResources().getDrawable(R.drawable.icon_menu);
        iconMenu.setImageDrawable(iconMenuDrawable);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this.target)
                .setContentView(iconMenu)
                .build();

        //create sub menu button
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this.target);

        ImageView iconSlideshow = new ImageView(this.target);
        ImageView iconGallery = new ImageView(this.target);
        ImageView iconPager = new ImageView(this.target);
        ImageView iconMovie = new ImageView(this.target);

        iconSlideshow.setImageDrawable(target.getResources().getDrawable(R.drawable.icon_slideshow));
        iconGallery.setImageDrawable(target.getResources().getDrawable(R.drawable.icon_gallery));
        iconPager.setImageDrawable(target.getResources().getDrawable(R.drawable.icon_pager));
        iconMovie.setImageDrawable(target.getResources().getDrawable(R.drawable.icon_movie));

        SubActionButton buttonSlideshow = itemBuilder.setContentView(iconSlideshow).build();
        SubActionButton buttonGallery = itemBuilder.setContentView(iconGallery).build();
        SubActionButton buttonPager = itemBuilder.setContentView(iconPager).build();
        SubActionButton buttonMovie = itemBuilder.setContentView(iconMovie).build();

        buttonSlideshow.setTag(MenuInfo.MENU_SLIDESHOW);
        buttonGallery.setTag(MenuInfo.MENU_GALLERY);
        buttonPager.setTag(MenuInfo.MENU_PAGER);
        buttonMovie.setTag(MenuInfo.MENU_VIDEO);

        //connect button to menu
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this.target)
                .addSubActionView(buttonSlideshow)
                .addSubActionView(buttonGallery)
                .addSubActionView(buttonPager)
                .addSubActionView(buttonMovie)
                .attachTo(actionButton)
                .build();


        this.menuClickedHandler = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent;

                int id = (int) v.getTag();

                switch (id) {
                    case MenuInfo.MENU_SLIDESHOW:
                        intent = new Intent(target.getApplicationContext(), SlideShowActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        target.startActivity(intent);
                        break;
                    case MenuInfo.MENU_GALLERY:
                        intent = new Intent(target.getApplicationContext(), GalleryActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        target.startActivity(intent);
                        break;
                    case MenuInfo.MENU_PAGER:
                        intent = new Intent(target.getApplicationContext(), PagerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        target.startActivity(intent);
                        break;
                    case MenuInfo.MENU_VIDEO:
                        intent = new Intent(target.getApplicationContext(), MovieActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        target.startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };

        buttonSlideshow.setOnClickListener(this.menuClickedHandler);
        buttonGallery.setOnClickListener(this.menuClickedHandler);
        buttonPager.setOnClickListener(this.menuClickedHandler);
        buttonMovie.setOnClickListener(this.menuClickedHandler);

    }

}
