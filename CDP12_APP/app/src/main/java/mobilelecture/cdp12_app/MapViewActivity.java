package mobilelecture.cdp12_app;


import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private DBManager dbManager = null;

    private ImageView imgMapView;

    private ClickableAreasImage clickableAreasImage;
    private TextView textView_wherePixel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_view);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);

        imgMapView = (ImageView) findViewById(R.id.imageView_mapview);
        textView_wherePixel = (TextView) findViewById(R.id.textView_where_mapview);


        Bitmap src1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.mapview1);
        Bitmap resized1 = Bitmap.createScaledBitmap(src1, 1500, 750, true);

        Bitmap src2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.map_pin_red);
        Bitmap resized2 = Bitmap.createScaledBitmap(src2, 70, 70, true);

        Bitmap src3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.man_con);
        Bitmap resized3 = Bitmap.createScaledBitmap(src3, 80, 80, true);



        ArrayList<ItemPosition> arr_item_position = new ArrayList<ItemPosition>();
        ArrayList<String> arr_cart =  dbManager.select_Cart_forMap();
        for(int i=0; i < arr_cart.size(); i++) {

            ArrayList<String> temp_cart = dbManager.select_Cart_forMap_byname(arr_cart.get(i));

            //Log.i("MapViewActivity","temp_cart  connername : " + temp_cart.get(0));

            ItemPosition temp_item_position = new ItemPosition();
            temp_item_position.location_num = Integer.valueOf(temp_cart.get(1));
            temp_item_position.conner = temp_cart.get(0);

            arr_item_position.add(temp_item_position);
        }

        Drawable drawable = new BitmapDrawable(overlayMark(resized1,resized2, resized3, arr_item_position));
        imgMapView.setImageDrawable(drawable);

        src1.recycle();
        src1 = null;
        src2.recycle();
        src2 = null;
        src3.recycle();
        src3 = null;
        resized1.recycle();
        resized1 = null;
        resized2.recycle();
        resized2 = null;
        resized3.recycle();
        resized3 = null;

        // Create your image
        clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imgMapView), this);

        // Initialize your clickable area list
        List<ClickableArea> clickableAreas = new ArrayList<>();
        clickableAreasImage.setClickableAreas(clickableAreas);

        // Define your clickable areas
        // parameter values (pixels): (x coordinate, y coordinate, width, height) and assign an object to it
        clickableAreas.add(new ClickableArea(200, 90, 40, 30, "행사3"));
        clickableAreas.add(new ClickableArea(220, 115, 30, 20, "라면"));
        clickableAreas.add(new ClickableArea(110, 5, 70, 20, "정육/계란1"));
        clickableAreas.add(new ClickableArea(2, 50, 25, 70, "채소/건나물"));
        clickableAreas.add(new ClickableArea(40, 75, 55, 25, "과일"));

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


    private Bitmap overlayMark(Bitmap bm_map, Bitmap bm_pin, Bitmap bm_man, ArrayList<ItemPosition> arr_item_position) {

        //   (ABS)    (Marker)
        // 155,200 -> 430,565   = (x * 2.77) / ( y * 2.82 )
        // 입구 : (155,200)
        // 장바구니 : (140,200)
        // 카트 : (80,220)

        Bitmap bmOverlay = Bitmap.createBitmap(bm_map.getWidth(), bm_map.getHeight(), bm_map.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bm_map, 0, 0, null);
        canvas.drawBitmap(bm_man, 430, 565, null);

        String pre_conner = "";
        for (int i=0 ; i< arr_item_position.size(); i++) {

            //if ( !pre_conner.equals(arr_item_position.get(i).conner)) {

                float goods_where = Float.valueOf(arr_item_position.get(i).location_num) / 5;

                ArrayList<Integer> arr_Corner_position = dbManager.select_CornerPosition_byConnerName(arr_item_position.get(i).conner);
                if(pre_conner.equals("채소/건나물")) continue;
                pre_conner = arr_item_position.get(i).conner;

                //Log.i("MapViewActivity","Pre_conner  : " + pre_conner);
                //Log.i("MapViewActivity"," location_num  : " + goods_where );
                //Log.i("MapViewActivity"," arr_item_position.get(i).conner  : " + arr_item_position.get(i).conner);


            canvas.drawBitmap(bm_pin, Integer.valueOf(String.format("%.0f", (arr_Corner_position.get(0) + arr_Corner_position.get(2) * goods_where) * 2.77)),
                    Integer.valueOf(String.format("%.0f", (arr_Corner_position.get(1) + (arr_Corner_position.get(3) / 2)) * 2.82 - 20)) , null);

            //}
        }
        return bmOverlay;
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


class ItemPosition {
    String conner;
    int location_num;
}