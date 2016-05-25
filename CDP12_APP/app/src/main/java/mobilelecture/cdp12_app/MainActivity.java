package mobilelecture.cdp12_app;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends TabActivity {

    private DBManager dbManager = null;

    //home tab
    private CustomAdapter_listview_event adapter_home;
    private CustomAdapter_listview_goods adapter_home2;
    private ListView listView_hometab;
    private ArrayList<String> arraylist_event;
    private ArrayList<String> arraylist_search;
    private ArrayList<String> arraylist_goods;

    //cart tab
    private CustomAdapter_listview_cart adapter_cart;
    private ListView listView_carttab;
    private ArrayList<String> arraylist_cart;
    private TextView textView_count_cart;


    //server-client
    private Socket client;
    private String ip = "211.229.100.109";
    private int port = 9999;
    private Thread thread;
    private ClientThread clientThread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);


        if( dbManager.select_IsThereInform().equals("0")) {
            Intent intent_inform = new Intent(MainActivity.this,UserInformActivity.class);
            startActivityForResult(intent_inform, 1);
        }
        else {

            //  --------------   server - client ----------
            handler = new Handler(){
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Bundle bundle = msg.getData();
                    Log.i("MainActivity","bundle_getStr : " + bundle.getString("msg"));

                }
            };
            connect();

            //----------------------------------  hometab spinner 생성 ------------------------------
            //스피너1 설정
            String spinner1_array[] = {"종류 선택", "해산", "정육", "과일", "채소"};
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
                    switch(position) {
                        case 1:
                            resetGoodsListView(position);
                            break;
                        case 2:
                            resetGoodsListView(position);
                            break;
                        case 3:
                            resetGoodsListView(position);
                            break;
                        case 4:
                            resetGoodsListView(position);
                            break;
                    }
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
                    //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            //물품검색

            ImageButton search_item = (ImageButton)findViewById(R.id.imageButton_hometab1);
            search_item.setOnClickListener(new View.OnClickListener() {
                EditText item_name = (EditText) findViewById(R.id.editText_hometab1);
                TextView tab = (TextView) findViewById(R.id.textView_hometab1);

                public void onClick(View v) {
                    tab.setText("검색 결과");
                    String Cname = item_name.getText().toString();
                    resetCartSearchView(Cname);
                    Toast.makeText(v.getContext(), Cname, Toast.LENGTH_SHORT).show();

                }
            });



            //----------------------------------  hometab listview 생성 ------------------------------
            resetEventListView();

            //----------  carttab listview 생성 ---------
            resetCartListView();


            //----------------------------------- cart tab 버튼 이벤트 ----------------------------
            // 전체삭제
            Button button_deleteall_cart = (Button) findViewById(R.id.button_deleteall_cart);
            button_deleteall_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("확인").setMessage("장바구니 전체 삭제 하실래요?")
                            .setNegativeButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (int i = 0; i < listView_carttab.getCount(); i++) {
                                        dbManager.delete_cart_byname(adapter_cart.getItem(i).getMenuName());
                                    }
                                    resetCartListView();
                                }
                            })
                            .setPositiveButton("아니오", null).show();
                }
            });


            // 길찾기
            Button button_searchMap_cart = (Button) findViewById(R.id.button_start_cart);
            button_searchMap_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_mapview = new Intent(MainActivity.this, RecoRangingActivity.class);
                    intent_mapview.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent_mapview);
                }
            });

            // 선택 삭제
            Button button_delete_cart = (Button) findViewById(R.id.button_delete_cart);
            button_delete_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("확인").setMessage("선택 항목 삭제 할래요?")
                            .setNegativeButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (int i = 0; i < listView_carttab.getCount(); i++) {
                                        if (adapter_cart.getChecked(i) == true) {
                                            dbManager.delete_cart_byname(adapter_cart.getItem(i).getMenuName());
                                        }
                                    }
                                    resetCartListView();
                                }
                            })
                            .setPositiveButton("아니오", null).show();
                }
            });

            //-------------------------------  ---  tab3 통계 -------------------  -----------
            // 서버에 메세지 보내기
            Button button_sendtest= (Button) findViewById(R.id.button_sendtest_tab3);
            button_sendtest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    clientThread.send("message send !!");
                }
            });

            //-------------------------------  ---  ETC tab 리스트뷰 생성 -------------------  -----------

            ArrayList<String> mGroupList = new ArrayList<String>();
            ArrayList<ArrayList<String>> mChildList = new ArrayList<ArrayList<String>>();
            ArrayList<String> mChildListContent = new ArrayList<String>();

            mGroupList.add("공지 사항");
            mGroupList.add("이용 안내");
            mGroupList.add("문의하기");
            mGroupList.add("개발자");

            mChildListContent.add("4월 2일");
            mChildListContent.add("3월 29일");
            mChildListContent.add("3월 2일");

            mChildList.add(mChildListContent);
            mChildList.add(mChildListContent);
            mChildList.add(mChildListContent);
            mChildList.add(mChildListContent);

            ExpandableListView mListView = (ExpandableListView) findViewById(R.id.listView_etctab1);
            mListView.setAdapter(new CustomAdapter_listview_etc(this, mGroupList, mChildList));

            // 그룹 클릭 했을 경우 이벤트
            mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    //Toast.makeText(getApplicationContext(), "g click = " + groupPosition, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            // 차일드 클릭 했을 경우 이벤트
            mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    //Toast.makeText(getApplicationContext(), "c click = " + childPosition, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            // 그룹이 닫힐 경우 이벤트
            mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {
                    //Toast.makeText(getApplicationContext(), "g Collapse = " + groupPosition, Toast.LENGTH_SHORT).show();
                }
            });

            // 그룹이 열릴 경우 이벤트
            mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    //Toast.makeText(getApplicationContext(), "g Expand = " + groupPosition, Toast.LENGTH_SHORT).show();
                }
            });


            //----------------------------------  tabmenu and icon 생성 ------------------------------
            //탭 메뉴
            TabHost tabHost = getTabHost();

            //TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("",getResources().getDrawable(R.drawable.android_con));
            TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("", getResources().getDrawable(R.drawable.home_con));
            tabSpecTab1.setContent(R.id.tab1);
            tabHost.addTab(tabSpecTab1);

            TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("", getResources().getDrawable(R.drawable.cart_con));
            tabSpecTab2.setContent(R.id.tab2);
            tabHost.addTab(tabSpecTab2);

            TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("", getResources().getDrawable(R.drawable.graph_con));
            tabSpecTab3.setContent(R.id.tab3);
            tabHost.addTab(tabSpecTab3);

            TabSpec tabSpecTab4 = tabHost.newTabSpec("TAB4").setIndicator("", getResources().getDrawable(R.drawable.menu_con));
            tabSpecTab4.setContent(R.id.tab4);
            tabHost.addTab(tabSpecTab4);

            tabHost.setCurrentTab(0);
            for (int i = 0; i < 4; i++) {
                tabHost.getTabWidget().getChildAt(i).setPadding(30, 30, 30, 30);
            }

        }
    }


    public void resetEventListView() {
        adapter_home = new CustomAdapter_listview_event(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_hometab = (ListView) findViewById(R.id.listView_hometab1);
        listView_hometab.setAdapter(adapter_home);

        arraylist_event = dbManager.select_Event();

        ArrayList<String> temp_event;

        for (int i = 0; i < arraylist_event.size(); i++) {
            String event_goods_name = arraylist_event.get(i);

            temp_event = dbManager.select_Event_byname(event_goods_name);
            String gid = dbManager.select_GoodsID_byname(event_goods_name);
            String icon_name = "goods" + gid;

            Listview_item_event u1 = new Listview_item_event(icon_name, event_goods_name, temp_event.get(0) + "원", temp_event.get(1) + "원");
            adapter_home.add(u1);
        }
        // Data가 변경 되있음을 알려준다.
        adapter_home.notifyDataSetChanged();
    }



    public void resetCartListView() {
        adapter_cart = new CustomAdapter_listview_cart(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_carttab = (ListView) findViewById(R.id.listView_carttab1);
        listView_carttab.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView_carttab.setAdapter(adapter_cart);

        arraylist_cart = dbManager.select_Cart();

        ArrayList<String> temp_cart;
        String EA = "";
        String Cname = "";

        for (int i = 0; i < arraylist_cart.size(); i++) {
            String cart_goods_name = arraylist_cart.get(i);

            String gid = dbManager.select_GoodsID_byname(cart_goods_name);
            String icon_name = "goods" + gid;
            Log.i("MainActivity","goodsid : " + icon_name);

            temp_cart = dbManager.select_Cart_byname(cart_goods_name);
            EA = dbManager.select_CartEA_byname(cart_goods_name);
            Cname = dbManager.select_CName_byInt(Integer.valueOf(temp_cart.get(2)));

            Listview_item_cart u1 = new Listview_item_cart(icon_name,Cname, cart_goods_name, EA + "개", Integer.valueOf(temp_cart.get(0))*Integer.valueOf(EA) + "g", Integer.valueOf(temp_cart.get(1))*Integer.valueOf(EA) + "원");
            adapter_cart.add(u1);

            adapter_cart.setCheckCount();
        }
        // Data가 변경 되있음을 알려준다.
        adapter_home.notifyDataSetChanged();

        //장바구니에 있는 상품들 갯수 텍스트뷰
        textView_count_cart = (TextView) findViewById(R.id.textView_cartcount);
        textView_count_cart.setText("총 " + adapter_cart.getCount() + "개 상품");
        //textView_count_cart.setText("총 " + listView_carttab.getCount() + "개 상품");
        //Log.i("MainActivity","총 개 상품 : " + adapter_cart.getCount());
    }

    //상품 스피너 출력
    public void resetGoodsListView(int Cid) {
        adapter_home2 = new CustomAdapter_listview_goods(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_hometab = (ListView) findViewById(R.id.listView_hometab1);
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
        adapter_home.notifyDataSetChanged();
    }


    //물품 검색
    public void resetCartSearchView(String Cname) {
        adapter_home2 = new CustomAdapter_listview_goods(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_hometab = (ListView) findViewById(R.id.listView_hometab1);
        listView_hometab.setAdapter(adapter_home2);

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




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) { // 액티비티가 정상적으로 종료되었을 경우
            String inform_check = "";
            if(requestCode==1) { // InformationInput에서 호출한 경우에만 처리합니다.
                inform_check = data.getStringExtra("Inform_exist");

                if(inform_check.equals("true")) {
                    Log.i("MainActivity","Inform exist");

                    Intent intent_main = new Intent(MainActivity.this, MainActivity.class);
                    intent_main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent_main);
                }
                else {
                    Log.i("MainActivity","Inform not exist");

                    moveTaskToBack(true);
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }

            }
        }
    }






    // ------------------------------- server-client  -------------------------------
    public void connect(){

        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);

                    clientThread = new ClientThread(client, handler);
                    clientThread.start();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();
    }
}