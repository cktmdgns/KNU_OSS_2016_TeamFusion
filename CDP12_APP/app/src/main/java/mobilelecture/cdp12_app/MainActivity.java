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



        // Adapter 생성
        CustomAdapter_listview1 adapter = new CustomAdapter_listview1(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        ListView userList = (ListView) findViewById(R.id.listView);
        userList.setAdapter(adapter);
        // Data 추가
        Listview1_item u1 = new Listview1_item(getResources().getDrawable(
                R.drawable.android_con), "삼겹살", "10000", "6700");
        adapter.add(u1);

        Listview1_item u2 = new Listview1_item(getResources().getDrawable(
                R.drawable.android_con), "청포도", "7310", "6290");
        adapter.add(u2);

        Listview1_item u3 = new Listview1_item(getResources().getDrawable(
                R.drawable.android_con), "왕교자", "14500", "12900");
        adapter.add(u3);

        // Data가 변경 되있음을 알려준다.
        adapter.notifyDataSetChanged();




        //스피너1 설정
        String spinner1_array[] = {"대분류", "대분류1", "대분류2"};
        ArrayAdapter<String> spinner_adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, spinner1_array);
        spinner_adapter1.setDropDownViewResource(R.layout.spinner_item);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        spinner1.setAdapter(spinner_adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinner2.setAdapter(spinner_adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String msg = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




        //탭 메뉴
        TabHost tabHost = getTabHost();

        //TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("",getResources().getDrawable(R.drawable.android_con));
        TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("",getResources().getDrawable(R.drawable.home_con));
        tabSpecTab1.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab1);

        TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("",getResources().getDrawable(R.drawable.cart_con));
        tabSpecTab2.setContent(R.id.tab2);
        tabHost.addTab(tabSpecTab2);

        TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("",getResources().getDrawable(R.drawable.graph_con));
        tabSpecTab3.setContent(R.id.tab3);
        tabHost.addTab(tabSpecTab3);

        TabSpec tabSpecTab4 = tabHost.newTabSpec("TAB4").setIndicator("",getResources().getDrawable(R.drawable.menu_con));
        tabSpecTab4.setContent(R.id.tab4);
        tabHost.addTab(tabSpecTab4);

        tabHost.setCurrentTab(0);

    }
}