package mobilelecture.cdp12_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class GoodsMapSearch extends AppCompatActivity {

    private DBManager dbManager = null;
    private ImageView imgMapView;
    private TextView textView_wherePixel;
    private TextView textView_cornergoodsinform;

    private String menuname = "";
    private String cornername = "";

    private String current_location_ID = "";
    private String current_location_LOC = "";

    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goods_map_search);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);

        textView_wherePixel = (TextView) findViewById(R.id.textView_where_goodssearch);
        imgMapView = (ImageView) findViewById(R.id.imageView_mapview_goodssearch);
        textView_cornergoodsinform = (TextView) findViewById(R.id.textView_connergoodsinforme_goodssearch);


        //Intent intent_mapview = new Intent(GoodsMapSearch.this, RecoRangingActivity.class);
        //intent_mapview.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //startActivityForResult(intent_mapview, 101);



        Intent intent_getLoc = getIntent();
        current_location_ID = intent_getLoc.getStringExtra("current_location_ID"); //마이너
        current_location_LOC = intent_getLoc.getStringExtra("current_location_LOC"); //거리
        menuname = intent_getLoc.getStringExtra("GoodsName");
        cornername = intent_getLoc.getStringExtra("ConerName");


        String whereman_name = dbManager.select_BeaconList(current_location_ID);

        if (whereman_name.equals("")) {
            textView_wherePixel.setText("블루투스 연결 상태를 확인해주세요.");
        }
        else {
            textView_wherePixel.setText("코너이름 :  " + whereman_name + "\n거리 :  " + current_location_LOC + "meter");
        }

        Log.i("GoodsMapSearch", " 비콘 값 전달1 : " + current_location_ID + " " + whereman_name + " " + current_location_LOC + "meter ");
        Log.i("GoodsMapSearch", " 비콘 값 전달2 : " + menuname + " " + cornername);



        ArrayList<Integer> arr_Corner_position_dest_whereman = new ArrayList<Integer>();
        if ( !whereman_name.equals("")) {
            arr_Corner_position_dest_whereman = dbManager.select_CornerPosition_byConnerName(whereman_name);
        }
        else {
            arr_Corner_position_dest_whereman.add(150);
            arr_Corner_position_dest_whereman.add(200);
            arr_Corner_position_dest_whereman.add(1);
            arr_Corner_position_dest_whereman.add(1);
        }


        ArrayList<Integer> arr_Corner_position_dest = dbManager.select_CornerPosition_byConnerName(cornername);
        Log.i("","");

        Bitmap src1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.mapview_0604);
        Bitmap resized1 = Bitmap.createScaledBitmap(src1, 1000, 500, true);

        Bitmap src2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.map_pin_red);
        Bitmap resized2 = Bitmap.createScaledBitmap(src2, 70, 70, true);

        Bitmap src3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.man_con);
        Bitmap resized3 = Bitmap.createScaledBitmap(src3, 80, 80, true);

        Drawable drawable = new BitmapDrawable(overlayMark(resized1, resized2, resized3, arr_Corner_position_dest, arr_Corner_position_dest_whereman));
        imgMapView.setImageDrawable(drawable);
        mAttacher = new PhotoViewAttacher(imgMapView);

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

    }


    private Bitmap overlayMark(Bitmap bm_map, Bitmap bm_pin, Bitmap bm_man, ArrayList<Integer> arr_item_position, ArrayList<Integer> whereman) {

        Bitmap bmOverlay = Bitmap.createBitmap(bm_map.getWidth(), bm_map.getHeight(), bm_map.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bm_map, 0, 0, null);

        canvas.drawBitmap(bm_pin, Integer.valueOf(String.format("%.0f", (arr_item_position.get(0) + arr_item_position.get(2) * 1/5 ) * 1.85)),
                Integer.valueOf(String.format("%.0f", (arr_item_position.get(1) + (arr_item_position.get(3) / 2)) * 1.7 - 30)), null);

        canvas.drawBitmap(bm_man, Integer.valueOf(String.format("%.0f", (whereman.get(0) * 1.8)))  , Integer.valueOf(String.format("%.0f", (whereman.get(1) * 1.7 - 30))), null);

        return bmOverlay;
    }


/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) { // 액티비티가 정상적으로 종료되었을 경우
            String inform_check = "";
            if(requestCode==101) { // InformationInput에서 호출한 경우에만 처리합니다.
                Intent intent_getLoc_bybeacon = getIntent();
                current_location_ID = intent_getLoc_bybeacon.getStringExtra("current_location_ID"); //마이너
                current_location_LOC = intent_getLoc_bybeacon.getStringExtra("current_location_LOC"); //거리
            }
        }
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_goods_map_search, menu);
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
