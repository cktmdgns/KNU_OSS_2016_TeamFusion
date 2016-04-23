package mobilelecture.cdp12_app;


import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class MapViewActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    //private String default_drawable_path = "android.resource://mobilelecture.cdp12_app/drawable/";
    //private PhotoViewAttacher mAttacher;


    private ImageView imgMapView;

    private ClickableAreasImage clickableAreasImage;
    private TextView textView_wherePixel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_view);


        imgMapView = (ImageView) findViewById(R.id.imageView_mapview);
        //Glide.with(this).load(Uri.parse(default_drawable_path + "mapview")).into(imgMapView);
        imgMapView.setImageResource(R.drawable.mapview1);
        textView_wherePixel = (TextView) findViewById(R.id.textView_where_mapview);


        // Create your image
        clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imgMapView), this);

        // Initialize your clickable area list
        List<ClickableArea> clickableAreas = new ArrayList<>();
        clickableAreasImage.setClickableAreas(clickableAreas);

        // Define your clickable areas
        // parameter values (pixels): (x coordinate, y coordinate, width, height) and assign an object to it
        //clickableAreas.add(new ClickableArea(500, 200, 125, 200, new Character('A')));
        //clickableAreas.add(new ClickableArea(800, 250, 130, 160, new Character('B')));
        clickableAreas.add(new ClickableArea(500, 200, 125, 200, "행사"));
        clickableAreas.add(new ClickableArea(800, 250, 130, 160, "장류"));
        //clickableAreas.add(new ClickableArea(500, 200, 125, 200, new Character("Homer", "Simpson")));
        //clickableAreas.add(new ClickableArea(600, 440, 130, 160, new Character("Bart", "Simpson")));


    }

    // Listen for touches on your images:
    @Override
    public void onClickableAreaTouched(Object item) {

        ArrayList<String> temp_arr = clickableAreasImage.getPixel();

        if (item instanceof String) {
            String text = "Touch Item ( " + (item).toString() +" ) Location :  X = "  + temp_arr.get(0) + "  /  Y = " + temp_arr.get(1) ;
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            textView_wherePixel.setText(text);
        }
        else {
            String text = "Touch Location :  X = " + temp_arr.get(0) + "  /  Y = " + temp_arr.get(1);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            textView_wherePixel.setText(text);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_view, menu);
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
