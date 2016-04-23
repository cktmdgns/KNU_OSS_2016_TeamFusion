package mobilelecture.cdp12_app;

import uk.co.senab.photoview.PhotoViewAttacher;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MapViewActivity extends AppCompatActivity {

    private String default_drawable_path = "android.resource://mobilelecture.cdp12_app/drawable/";

    private ImageView imgMapView;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_view);


        imgMapView = (ImageView) findViewById(R.id.imageView_mapview);
        Glide.with(this).load(Uri.parse(default_drawable_path + "mapview")).into(imgMapView);
        mAttacher = new PhotoViewAttacher(imgMapView);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //mAttacher.update();

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
