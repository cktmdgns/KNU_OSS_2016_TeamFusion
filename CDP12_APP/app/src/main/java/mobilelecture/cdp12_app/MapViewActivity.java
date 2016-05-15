package mobilelecture.cdp12_app;


import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;

import android.Manifest;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;




public class MapViewActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    //private String default_drawable_path = "android.resource://mobilelecture.cdp12_app/drawable/";

    private DBManager dbManager = null;
    private ImageView imgMapView;
    private ClickableAreasImage clickableAreasImage;
    private TextView textView_wherePixel;

    private String current_location_ID = "";
    private String current_location_LOC = "";
    private String curcorname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_view);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);

        textView_wherePixel = (TextView) findViewById(R.id.textView_where_mapview);
        imgMapView = (ImageView) findViewById(R.id.imageView_mapview);



        Intent intent_getLoc = getIntent();
        current_location_ID = intent_getLoc.getStringExtra("current_location_ID");
        current_location_LOC = intent_getLoc.getStringExtra("current_location_LOC");
        if(current_location_ID.equals("16646")) {
            curcorname = "정육1";
        }
        else if (current_location_ID.equals("16647")) {
            curcorname = "과일";
        }
        else if (current_location_ID.equals("16648")) {
            curcorname = "채소";
        }

        if (curcorname.equals("")) {
            textView_wherePixel.setText("블루투스 연결 상태를 확인해주세요.");
        }
        else {
            textView_wherePixel.setText("가장 가까운 비콘위치\n코너이름 :  " + curcorname + "\n거리 :  " + current_location_LOC + "meter");
            Log.i("MapViewActivity", "비콘 연동 테스트  " + current_location_ID + "  " + current_location_LOC + "meter");
        }




        Bitmap src1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.mapview1);
        Bitmap resized1 = Bitmap.createScaledBitmap(src1, 1000, 500, true);

        Bitmap src2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.map_pin_red);
        Bitmap resized2 = Bitmap.createScaledBitmap(src2, 70, 70, true);

        Bitmap src3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.man_con);
        Bitmap resized3 = Bitmap.createScaledBitmap(src3, 80, 80, true);


        ConnerPosition whereman = new ConnerPosition();
        if (curcorname.equals("정육1")) {
            whereman.pic_x = 145;
            whereman.pic_y = 40;
        }
        else if (curcorname.equals("과일")) {
            whereman.pic_x = 105;
            whereman.pic_y = 120;
        }
        else if (curcorname.equals("채소")) {
            whereman.pic_x = 35;
            whereman.pic_y = 120;
        }
        else {
            whereman.pic_x = 160;
            whereman.pic_y = 200;
        }


        ArrayList<ItemPosition> arr_item_position = new ArrayList<ItemPosition>();
        ItemPosition temp_item_position0 = new ItemPosition();
        temp_item_position0.conner_name = "Start";
        temp_item_position0.goods_location = 5;
        temp_item_position0.loc_x = whereman.pic_x;
        temp_item_position0.loc_y = whereman.pic_y;
        temp_item_position0.lenth_x = 1;
        temp_item_position0.lenth_y = 1;
        arr_item_position.add(temp_item_position0);

        ItemPosition temp_item_position1 = new ItemPosition();
        temp_item_position1.conner_name = "Counter";
        temp_item_position1.goods_location = 5;
        temp_item_position1.loc_x = 300;
        temp_item_position1.loc_y = 200;
        temp_item_position1.lenth_x = 1;
        temp_item_position1.lenth_y = 1;
        arr_item_position.add(temp_item_position1);

        ArrayList<String> arr_cart = dbManager.select_Cart_forMap();
        for (int i = 0; i < arr_cart.size(); i++) {

            ArrayList<String> temp_cart = dbManager.select_Cart_forMap_byname(arr_cart.get(i));

            ItemPosition temp_item_position = new ItemPosition();
            ArrayList<Integer> arr_Corner_position_dest = dbManager.select_CornerPosition_byConnerName(temp_cart.get(0));
            temp_item_position.conner_name = temp_cart.get(0);
            temp_item_position.goods_location = Integer.valueOf(temp_cart.get(1));
            temp_item_position.loc_x = arr_Corner_position_dest.get(0);
            temp_item_position.loc_y = arr_Corner_position_dest.get(1);
            temp_item_position.lenth_x = arr_Corner_position_dest.get(2);
            temp_item_position.lenth_y = arr_Corner_position_dest.get(3);
            arr_item_position.add(temp_item_position);
        }

        ArrayList<ConnerPosition> connerPositions = getTsp(arr_item_position, whereman);

        Drawable drawable = new BitmapDrawable(overlayMark(resized1, resized2, resized3, arr_item_position, connerPositions));
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
            String text = "Touch Item ( " + (item).toString() + " ) Location :  X = " + temp_arr.get(0) + "  /  Y = " + temp_arr.get(1);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        } else {
            String text = "Touch Location :  X = " + temp_arr.get(0) + "  /  Y = " + temp_arr.get(1);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }


    private Bitmap overlayMark(Bitmap bm_map, Bitmap bm_pin, Bitmap bm_man, ArrayList<ItemPosition> arr_item_position, ArrayList<ConnerPosition> connerPositions) {

        Paint mPaint = new Paint();

        //   (ABS)    (Marker)
        // 155,200 -> 430,565   = (x * 2.77) / ( y * 2.82 )
        // 입구 : (155,200)
        // 장바구니 : (140,200)
        // 카트 : (80,220)

        Bitmap bmOverlay = Bitmap.createBitmap(bm_map.getWidth(), bm_map.getHeight(), bm_map.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bm_map, 0, 0, null);
        canvas.drawBitmap(bm_man, connerPositions.get(0).pic_x * 2 - 20, connerPositions.get(0).pic_y * 2 - 20, null);

        mPaint.setColor(0xFFFF2B21);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);

        for (int i = 0; i < connerPositions.size() - 1; i++) {
            canvas.drawLine(connerPositions.get(i).pic_x * 2, connerPositions.get(i).pic_y * 2 + 15, connerPositions.get(i + 1).pic_x * 2, connerPositions.get(i + 1).pic_y * 2 + 15, mPaint);
        }

        String pre_conner = "";
        for (int i = 0; i < arr_item_position.size(); i++) {

            float goods_where = Float.valueOf(arr_item_position.get(i).goods_location) / 5;

            if (pre_conner.equals("채소/건나물")) continue;
            pre_conner = arr_item_position.get(i).conner_name;

            if(pre_conner.equals("Start") || pre_conner.equals("Counter")) {
                continue;
            }
            canvas.drawBitmap(bm_pin, Integer.valueOf(String.format("%.0f", (arr_item_position.get(i).loc_x + arr_item_position.get(i).lenth_x * goods_where) * 1.85)),
                    Integer.valueOf(String.format("%.0f", (arr_item_position.get(i).loc_y + (arr_item_position.get(i).lenth_y / 2)) * 1.7 - 30)), null);

        }
        /*

        ArrayList<ConnerPosition> vertex_positions = dbManager.select_conners();
        ArrayList<VertexLenth> vertex_lenth = new ArrayList<VertexLenth>();
        ConnerPosition cur_position;
        int next_vertex;
        int count = 0;
        for(int i=0; i<connerPositions.size();i++) {

            cur_position = connerPositions.get(i);
            Log.i("MapViewActivity","aaaaaaaaa");
            next_vertex = 99;
            while (count < 10) {

                for (int j = 0; j < vertex_positions.size(); j++) {
                    VertexLenth temp_vertex_lenth = new VertexLenth();
                    temp_vertex_lenth.vertex_num = j;
                    temp_vertex_lenth.vertex_lenth = Math.pow(vertex_positions.get(j).pic_x - cur_position.pic_x, 2) + Math.pow(vertex_positions.get(j).pic_y - cur_position.pic_y, 2);
                    vertex_lenth.add(temp_vertex_lenth);
                }
                for (int j = 0; j < vertex_lenth.size(); j++) {
                    Collections.sort(vertex_lenth, new Comparator<VertexLenth>() {
                        public int compare(VertexLenth obj1, VertexLenth obj2) {
                            // TODO Auto-generated method stub
                            return (obj1.vertex_lenth < obj2.vertex_lenth) ? -1 : (obj1.vertex_lenth > obj2.vertex_lenth) ? 1 : 0;
                        }
                    });
                }
                Log.i("MapViewActivity", "vertex : " + vertex_lenth.get(0).vertex_num + " " + vertex_lenth.get(0).vertex_lenth);
                Log.i("MapViewActivity", "vertex : " + vertex_lenth.get(1).vertex_num + " " + vertex_lenth.get(1).vertex_lenth);
                Log.i("MapViewActivity", "vertex : " + vertex_lenth.get(2).vertex_num + " " + vertex_lenth.get(2).vertex_lenth);

                if(vertex_lenth.get(0).vertex_num == next_vertex) {
                    break;
                }

                double temp_result = 1000000.0;

                for (int j = 0; j < 3; j++) {
                    double temp = Math.pow(vertex_positions.get(vertex_lenth.get(j).vertex_num).pic_x - cur_position.pic_x, 2) + Math.pow(vertex_positions.get(vertex_lenth.get(j).vertex_num).pic_y - cur_position.pic_y, 2);
                    if (temp_result > temp) {
                        temp_result = temp;
                        next_vertex = vertex_lenth.get(j).vertex_num;
                    }
                }
                canvas.drawLine(cur_position.pic_x * 2, cur_position.pic_y * 2, vertex_positions.get(next_vertex).pic_x * 2, vertex_positions.get(next_vertex).pic_y * 2, mPaint);
                cur_position = vertex_positions.get(next_vertex);
                count++;
            }
        }
        */

        return bmOverlay;
    }

    public ArrayList<ConnerPosition> getTsp(ArrayList<ItemPosition> arr_item_position, ConnerPosition whereman) {

        String result_corner_name = "";
        String result_path = "";
        String temp_path = "";
        String preconner_start = "";
        String preconner_dest = "";
        ArrayList<String> nosame_corner = new ArrayList<String>();
        int[] best_tour = new int[1];
        boolean tour_check = false;
        double result = 0.0;
        ArrayList<ConnerPosition> temp_conner = new ArrayList<ConnerPosition>();
        ArrayList<ConnerPosition> return_conner = new ArrayList<ConnerPosition>();
        //(Start : 160,200) , (Counter : 300,200)


        // start to dest1
        for (int i = 0; i < arr_item_position.size(); i++) {
            if (preconner_start.equals(arr_item_position.get(i).conner_name)) {
                continue;
            }
            preconner_start = arr_item_position.get(i).conner_name;
            nosame_corner.add(arr_item_position.get(i).conner_name);
            result = (Math.pow(((  arr_item_position.get(i).loc_x + arr_item_position.get(i).lenth_x / 2) - whereman.pic_x), 2) + Math.pow(((  arr_item_position.get(i).loc_y + arr_item_position.get(i).lenth_y / 2) - whereman.pic_y), 2)) / 5;
            temp_path = temp_path + " " + result;

            ConnerPosition temp_connerPosition = new ConnerPosition();
            temp_connerPosition.pic_x = (  arr_item_position.get(i).loc_x + arr_item_position.get(i).lenth_x / 2);
            temp_connerPosition.pic_y = (  arr_item_position.get(i).loc_y + arr_item_position.get(i).lenth_y / 2);
            temp_conner.add(temp_connerPosition);
        }


        // dest1 to dest2
        for (int i = 0; i < arr_item_position.size(); i++) {
            if (preconner_start.equals(arr_item_position.get(i).conner_name)) {
                continue;
            }
            preconner_start = arr_item_position.get(i).conner_name;
            int startx = arr_item_position.get(i).loc_x + arr_item_position.get(i).lenth_x / 2;
            int starty = arr_item_position.get(i).loc_y + arr_item_position.get(i).lenth_y / 2;

            result = (Math.pow(startx - whereman.pic_x, 2) + Math.pow(starty - whereman.pic_y, 2)) / 5;
            //temp_path = "" + result;
            temp_path = "";


            for (int j = 0; j < arr_item_position.size(); j++) {
                if (preconner_dest.equals(arr_item_position.get(j).conner_name)) {
                    continue;
                }
                preconner_dest = arr_item_position.get(j).conner_name;
                result = (Math.pow(((arr_item_position.get(j).loc_x + arr_item_position.get(j).lenth_x / 2) - startx), 2) + Math.pow(((arr_item_position.get(j).loc_y + arr_item_position.get(j).lenth_y / 2) - starty), 2)) / 5;

                temp_path = temp_path + " " + result;

                //Log.i("MapViewActivity","i :" + arr_item_position.get(i).conner + "  j : " + arr_item_position.get(j).conner);
            }
            Log.i("MapViewActivity", "temp_path : " + temp_path);
            result_path = result_path + temp_path + "\n";
        }


        for (int k = 0; k < 10; k++) {
            Tsp tsp = new Tsp();
            tsp.readStringPath(result_path);
            for (int i = 0; i < 10; i++) {

                if (tour_check == true) {
                    break;
                } else {
                    best_tour = tsp.solve(i);
                    if (i > 3 && best_tour[0] == 0) {
                        tour_check = true;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < best_tour.length; i++) {
            result_corner_name = result_corner_name + " " + nosame_corner.get(best_tour[i]);
            return_conner.add(temp_conner.get(best_tour[i]));
        }
        Log.i("MapViewActivity", "best Tour : " + result_corner_name);

        return return_conner;
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
    String conner_name;
    int goods_location;
    int loc_x;
    int lenth_x;
    int loc_y;
    int lenth_y;
}

class ConnerPosition {
    int pic_x;
    int pic_y;
}

class VertexLenth {
    int vertex_num;
    double vertex_lenth;
}