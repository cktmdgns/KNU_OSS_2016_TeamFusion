package mobilelecture.cdp12_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GoodsSearchActivity extends AppCompatActivity {

    private DBManager dbManager = null;

    private ArrayList<String> arraylist_search;
    private ArrayList<String> arraylist_goods;

    private CustomAdapter_listview_goods adapter_home2;
    private ListView listView_hometab;

    private String cornername;
    private String goodsname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goods_search);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);

        Intent intent_getLoc = getIntent();
        cornername = intent_getLoc.getStringExtra("CornerName");
        goodsname = intent_getLoc.getStringExtra("GoodsName");
        if( !cornername.equals("")) {
            resetGoodsListView(Integer.valueOf(cornername));
        }
        else {
            resetCartSearchView(goodsname);
        }


        //----------------------------------  hometab spinner 생성 ------------------------------
        ArrayList<String> spinner1_array = dbManager.select_Corners_forspinner();
        ArrayAdapter<String> spinner_adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinner1_array);
        spinner_adapter1.setDropDownViewResource(R.layout.spinner_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_goodsSearch);


        spinner1.setAdapter(spinner_adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position > 0) {
                    resetGoodsListView(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //물품검색
        ImageButton search_item = (ImageButton)findViewById(R.id.imageButton_goodsSearch);
        search_item.setOnClickListener(new View.OnClickListener() {
            EditText item_name = (EditText) findViewById(R.id.editText_goodsSearch);
            TextView tab = (TextView) findViewById(R.id.textView_goodsSearch);

            public void onClick(View v) {
                tab.setText("검색 결과");
                String Cname = item_name.getText().toString();
                resetCartSearchView(Cname);
                Toast.makeText(v.getContext(), Cname, Toast.LENGTH_SHORT).show();

            }
        });


        HorizontalListView listview_horizon = (HorizontalListView) findViewById(R.id.listView_horizon);
        listview_horizon.setAdapter(mAdapter);

    }

    private String[] data = new String[] {
            "t1", "t2", "t3"
    };

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizon_listitem, parent, false);
            //TextView title = (TextView) retval.findViewById(R.id.)


            return retval;
        }
    };










    //상품 스피너 출력
    public void resetGoodsListView(int Cid) {
        adapter_home2 = new CustomAdapter_listview_goods(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_hometab = (ListView) findViewById(R.id.listView_goodsSearch);
        listView_hometab.setAdapter(adapter_home2);

        arraylist_goods = dbManager.select_Classcification(Cid);

        ArrayList<String> temp_goods;

        for (int i = 0; i < arraylist_goods.size(); i++) {
            String event_goods_name = arraylist_goods.get(i);

            temp_goods = dbManager.select_Goods_byname(event_goods_name);
            String gid = dbManager.select_GoodsID_byname(event_goods_name);
            String icon_name = "goods" + gid;

            Listview_item_goods u1 = new Listview_item_goods(icon_name, event_goods_name, temp_goods.get(0) + "g", temp_goods.get(1) + "원");
            adapter_home2.add(u1);
        }
        // Data가 변경 되있음을 알려준다.
        adapter_home2.notifyDataSetChanged();
    }


    //물품 검색
    public void resetCartSearchView(String Cname) {
        adapter_home2 = new CustomAdapter_listview_goods(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_hometab = (ListView) findViewById(R.id.listView_goodsSearch);
        listView_hometab.setAdapter(adapter_home2);

        if(Cname.equals("")) {
            arraylist_goods = dbManager.select_Goods();

            ArrayList<String> temp_goods;

            for (int i = 0; i < arraylist_goods.size(); i++) {
                String event_goods_name = arraylist_goods.get(i);

                temp_goods = dbManager.select_Goods_byname(event_goods_name);
                String gid = dbManager.select_GoodsID_byname(event_goods_name);
                String icon_name = "goods" + gid;

                Listview_item_goods u1 = new Listview_item_goods(icon_name, event_goods_name, temp_goods.get(0) + "g", temp_goods.get(1) + "원");
                adapter_home2.add(u1);
            }
            // Data가 변경 되있음을 알려준다.
            adapter_home2.notifyDataSetChanged();
        }
        else {
            arraylist_search = dbManager.select_Search(Cname);

            ArrayList<String> temp_search;

            for (int i = 0; i < arraylist_search.size(); i++) {
                String event_goods_name = arraylist_search.get(i);
                String gid = dbManager.select_GoodsID_byname(event_goods_name);
                String icon_name = "goods" + gid;

                temp_search = dbManager.select_Goods_byname(event_goods_name);

                Listview_item_goods u1 = new Listview_item_goods(icon_name, event_goods_name, temp_search.get(0) + "g", temp_search.get(1) + "원");
                adapter_home2.add(u1);
            }
            // Data가 변경 되있음을 알려준다.
            adapter_home2.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_goods_search, menu);
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
