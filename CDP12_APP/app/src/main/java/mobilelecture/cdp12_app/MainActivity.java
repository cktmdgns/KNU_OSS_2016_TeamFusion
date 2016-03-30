package mobilelecture.cdp12_app;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----------------------------------  hometab listview 생성 ------------------------------
        // Adapter 생성
        CustomAdapter_listview1 adapter_home = new CustomAdapter_listview1(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        ListView listView_hometab = (ListView) findViewById(R.id.listView_hometab1);
        listView_hometab.setAdapter(adapter_home);
        // Data 추가
        Listview1_item u1 = new Listview1_item(getResources().getDrawable(
                R.drawable.android_con), "삼겹살 (200g)", "10000원", "6700원");
        adapter_home.add(u1);
        Listview1_item u2 = new Listview1_item(getResources().getDrawable(
                R.drawable.android_con), "청포도 한송이", "7310원", "6290원");
        adapter_home.add(u2);
        Listview1_item u3 = new Listview1_item(getResources().getDrawable(
                R.drawable.android_con), "왕교자 (100g)", "14500원", "12900원");
        adapter_home.add(u3);
        // Data가 변경 되있음을 알려준다.
        adapter_home.notifyDataSetChanged();

        //----------------------------------  carttab listview 생성 ------------------------------
        // Adapter 생성
        CustomAdapter_listview_cart adapter_cart = new CustomAdapter_listview_cart(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        ListView listView_carttab = (ListView) findViewById(R.id.listView_carttab1);
        listView_carttab.setAdapter(adapter_cart);
        // Data 추가
        Listview_item_cart c1 = new Listview_item_cart(getResources().getDrawable(
                R.drawable.android_con), "삼겹살 (100g)", "10000원", "6700원");
        adapter_cart.add(c1);
        Listview_item_cart c2 = new Listview_item_cart(getResources().getDrawable(
                R.drawable.android_con), "청포도 한송이", "7310원", "6290원");
        adapter_cart.add(c2);
        Listview_item_cart c3 = new Listview_item_cart(getResources().getDrawable(
                R.drawable.android_con), "왕교자 (100g)", "14500원", "12900원");
        adapter_cart.add(c3);
        // Data가 변경 되있음을 알려준다.
        adapter_cart.notifyDataSetChanged();




        //----------------------------------  hometab spinner 생성 ------------------------------
        //스피너1 설정
        String spinner1_array[] = {"대분류", "대분류1", "대분류2"};
        ArrayAdapter<String> spinner_adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinner1_array);
        spinner_adapter1.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_hometab1);
        Spinner spinner1_2 = (Spinner) findViewById(R.id.spinner_carttab1);

        spinner1.setAdapter(spinner_adapter1);
        spinner1_2.setAdapter(spinner_adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner1_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //스피너2 설정
        String spinner2_array[] = {"중분류", "중분류1", "중분류2"};
        ArrayAdapter<String> spinner_adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinner2_array);
        spinner_adapter2.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_hometab2);
        Spinner spinner2_2 = (Spinner) findViewById(R.id.spinner_carttab2);

        spinner2.setAdapter(spinner_adapter2);
        spinner2_2.setAdapter(spinner_adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });



        //----------------------------------  tabmenu and icon 생성 ------------------------------
        //탭 메뉴
        TabHost tabHost = getTabHost();


        //TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("",getResources().getDrawable(R.drawable.android_con));
        TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("",getResources().getDrawable(R.drawable.home_con));
        tabSpecTab1.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab1);

        TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("", getResources().getDrawable(R.drawable.cart_con));
        tabSpecTab2.setContent(R.id.tab2);
        tabHost.addTab(tabSpecTab2);

        TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("",getResources().getDrawable(R.drawable.graph_con));
        tabSpecTab3.setContent(R.id.tab3);
        tabHost.addTab(tabSpecTab3);

        TabSpec tabSpecTab4 = tabHost.newTabSpec("TAB4").setIndicator("",getResources().getDrawable(R.drawable.menu_con));
        tabSpecTab4.setContent(R.id.tab4);
        tabHost.addTab(tabSpecTab4);

        tabHost.setCurrentTab(0);
        for (int i = 0; i < 4; i++){
            tabHost.getTabWidget().getChildAt(i).setPadding(30,30,30,30);
        }

    }
}