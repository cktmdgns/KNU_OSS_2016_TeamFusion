package mobilelecture.cdp12_app;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends TabActivity {

    private DBManager dbManager = null;

    private String curdate = "";
    private String curyear = "";
    private String curmonth = "";
    private String curday = "";
    private int tempyear = 0;
    private int tempmonth = 0;

    //home tab
    private CustomAdapter_listview_event adapter_home;
    private ListView listView_hometab;
    private ArrayList<String> arraylist_event;

    //cart tab
    private CustomAdapter_listview_cart adapter_cart;
    private ListView listView_carttab;
    private ArrayList<String> arraylist_cart;
    private TextView textView_count_cart;

    //tab3
    private CustomAdapter_listview_tab3 adapter_tab3;
    private ListView listView_tab3;
    private ArrayList<Listview_item_tab3> arraylist_shoping_list;


    //server-client
    private Socket client;
    private String ip = "211.229.100.109";
    private int port = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(getApplicationContext(), "test.db", null, 1);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        //SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        curdate = CurDateFormat.format(date);
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        curyear = CurYearFormat.format(date);
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        curmonth = CurMonthFormat.format(date);
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        curday = CurDayFormat.format(date);


        if( dbManager.select_IsThereInform().equals("0")) {
            Intent intent_inform = new Intent(MainActivity.this,UserInformActivity.class);
            startActivityForResult(intent_inform, 1);
        }
        else {

            if(dbManager.select_Goods().size() == 0) {
                new HttpGetTask_Item_event().execute();
                new HttpGetTask_Item_goods().execute();
                Log.i("MainActivity","Http Test1");
            }
            else {
                //if(dbManager.select_Goods().size() == 0) {
                //    dbManager.insert_init_event();
                //    dbManager.insert_init_goods();
                //    Log.i("MainActivity", "Http Test2");
                //}

                //----------------------------------  hometab spinner 생성 ------------------------------
                //스피너1 설정
                ArrayList<String> spinner1_array = dbManager.select_Corners_forspinner();
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
                        if(position > 0) {
                            Intent intent_search_corner = new Intent(getApplicationContext(),GoodsSearchActivity.class);
                            intent_search_corner.putExtra("CornerName", "" + position);
                            intent_search_corner.putExtra("GoodsName", "");
                            intent_search_corner.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent_search_corner);
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
                        if (position > 0) {
                            //resetGoodsListView(position);
                            Intent intent_search_corner = new Intent(getApplicationContext(), GoodsSearchActivity.class);
                            intent_search_corner.putExtra("CornerName", "" + position);
                            intent_search_corner.putExtra("GoodsName", "");
                            intent_search_corner.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent_search_corner);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


                //물품검색1
                ImageButton search_item = (ImageButton)findViewById(R.id.imageButton_hometab1);
                search_item.setOnClickListener(new View.OnClickListener() {
                    EditText item_name = (EditText) findViewById(R.id.editText_hometab1);
                    TextView tab = (TextView) findViewById(R.id.textView_hometab1);

                    public void onClick(View v) {
                        tab.setText("검색 결과");
                        String Cname = item_name.getText().toString();
                        Intent intent_search_corner = new Intent(getApplicationContext(), GoodsSearchActivity.class);
                        intent_search_corner.putExtra("CornerName", "");
                        intent_search_corner.putExtra("GoodsName", "" + Cname);
                        intent_search_corner.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent_search_corner);
                        Toast.makeText(v.getContext(), Cname, Toast.LENGTH_SHORT).show();

                    }
                });

                //물품검색2
                ImageButton search_item2 = (ImageButton)findViewById(R.id.imageButton_carttab1);
                search_item2.setOnClickListener(new View.OnClickListener() {
                    EditText item_name = (EditText) findViewById(R.id.editText_carttab1);
                    TextView tab = (TextView) findViewById(R.id.textView_carttab1);

                    public void onClick(View v) {
                        tab.setText("검색 결과");
                        String Cname = item_name.getText().toString();
                        Intent intent_search_corner = new Intent(getApplicationContext(), GoodsSearchActivity.class);
                        intent_search_corner.putExtra("CornerName", "");
                        intent_search_corner.putExtra("GoodsName", "" + Cname);
                        intent_search_corner.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent_search_corner);
                        Toast.makeText(v.getContext(), Cname, Toast.LENGTH_SHORT).show();

                    }
                });

                //----------------------------------  hometab listview 생성 ------------------------------
                resetEventListView();

                //----------  carttab listview 생성 ---------
                resetCartListView();


                //----------------------------------- cart tab 버튼 이벤트 ----------------------------
                // 화면갱신
                ImageButton reset_cart = (ImageButton)findViewById(R.id.imageButton_reset_carttab1);
                reset_cart.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        resetCartListView();
                        resetShopingList();
                    }
                });

                // 전체삭제
                Button button_deleteall_cart = (Button) findViewById(R.id.button_deleteall_cart);
                button_deleteall_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("확인").setMessage("장바구니 전체 삭제 하실래요?")
                                .setNegativeButton("가계부에 넣고 삭제", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for (int i = 0; i < listView_carttab.getCount(); i++) {
                                            dbManager.insert_shopingList(curdate, curyear, curmonth, curday,
                                                    adapter_cart.getItem(i).getMenuName(),
                                                    adapter_cart.getItem(i).getPrice(),
                                                    adapter_cart.getEA(i));
                                            dbManager.delete_cart_byname(adapter_cart.getItem(i).getMenuName());
                                        }
                                        resetCartListView();
                                        resetShopingList();
                                    }
                                })
                                .setNeutralButton("전체 삭제", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for (int i = 0; i < listView_carttab.getCount(); i++) {
                                            dbManager.delete_cart_byname(adapter_cart.getItem(i).getMenuName());
                                        }
                                        resetCartListView();
                                        resetShopingList();
                                    }
                                })
                                .setPositiveButton("취소", null).show();
                    }
                });


                // 길찾기
                Button button_searchMap_cart = (Button) findViewById(R.id.button_start_cart);
                button_searchMap_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_mapview = new Intent(MainActivity.this, RecoRangingActivity.class);
                        intent_mapview.putExtra("TYPE", "" + 0);
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
                                .setNegativeButton("가계부에 넣고 삭제", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                for (int i = 0; i < listView_carttab.getCount(); i++) {
                                                    if (adapter_cart.getChecked(i) == true) {
                                                        dbManager.insert_shopingList(curdate,curyear,curmonth,curday,
                                                                adapter_cart.getItem(i).getMenuName(),
                                                                adapter_cart.getItem(i).getPrice(),
                                                                adapter_cart.getEA(i));
                                                        dbManager.delete_cart_byname(adapter_cart.getItem(i).getMenuName());
                                                    }
                                                }

                                                resetCartListView();
                                            }
                                        }

                                )
                                .

                                        setNeutralButton("선택항목 삭제", new DialogInterface.OnClickListener() {
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
                                .setPositiveButton("취소", null).show();
                    }
                });

                //----------------------------------  tab3 -------------------  -----------
                final TextView textView_date = (TextView) findViewById(R.id.textview_date_tab3);
                tempyear = Integer.valueOf(curyear);
                tempmonth = Integer.valueOf(curmonth);
                textView_date.setText(tempyear + "년 " + tempmonth + "월");

                ////////////
                resetShopingList();

                Button button_prev_tab3 = (Button) findViewById(R.id.button_prev_tab3);
                button_prev_tab3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tempmonth > 1) {
                            tempmonth--;
                        } else {
                            tempyear--;
                            tempmonth = 12;
                        }
                        textView_date.setText(tempyear + "년 " + tempmonth + "월");

                        resetShopingList();
                    }
                });

                Button button_next_tab3 = (Button) findViewById(R.id.button_next_tab3);
                button_next_tab3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tempmonth < 12) {
                            tempmonth++;
                        } else {
                            tempyear++;
                            tempmonth = 1;
                        }
                        textView_date.setText(tempyear + "년 " + tempmonth + "월");

                        resetShopingList();
                    }
                });







                //-------------------------------  ---  ETC tab 리스트뷰 생성 -------------------  -----------

                ArrayList < String > mGroupList = new ArrayList<String>();
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
                final TabHost tabHost = getTabHost();

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


                tabHost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetCartListView();
                    }
                });

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
        adapter_cart.notifyDataSetChanged();

        //장바구니에 있는 상품들 갯수 텍스트뷰
        textView_count_cart = (TextView) findViewById(R.id.textView_cartcount);
        textView_count_cart.setText("총 " + adapter_cart.getCount() + "개 상품");
        //textView_count_cart.setText("총 " + listView_carttab.getCount() + "개 상품");
        //Log.i("MainActivity","총 개 상품 : " + adapter_cart.getCount());
    }

    public void resetShopingList() {
        String input_month = "";
        if(tempmonth < 10) {
            input_month = "0" + tempmonth;
        }
        else {
            input_month = "" + tempmonth;
        }

        int totalprice = 0;

        adapter_tab3 = new CustomAdapter_listview_tab3(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        listView_tab3 = (ListView) findViewById(R.id.listView_tab3);
        listView_tab3.setAdapter(adapter_tab3);

        Log.i("", "Date :" + String.valueOf(tempyear) + input_month);
        arraylist_shoping_list = dbManager.select_shoping_list(String.valueOf(tempyear), input_month);
        //arraylist_shoping_list = dbManager.select_shoping_list_all();

        for (int i=0; i<arraylist_shoping_list.size(); i++) {
            Log.i("","Shop List :" + arraylist_shoping_list.get(i));
            Listview_item_tab3 u1 = new Listview_item_tab3(arraylist_shoping_list.get(i).getDate().substring(10,13),
                    arraylist_shoping_list.get(i).getBody(),
                    arraylist_shoping_list.get(i).getEA(),
                    arraylist_shoping_list.get(i).getPrice());
            adapter_tab3.add(u1);

            totalprice = totalprice + Integer.valueOf(arraylist_shoping_list.get(i).getPrice().substring(0,arraylist_shoping_list.get(i).getPrice().length() -1));
        }
        adapter_tab3.notifyDataSetChanged();

        TextView totalprice_tab3 = (TextView) findViewById(R.id.textview_totalprice_tab3);
        totalprice_tab3.setText("이번달 합계 :  " + totalprice + "원");
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
    class HttpGetTask_Item_event extends AsyncTask<Integer, Void, Item_event[]> {
        @Override
        protected Item_event[] doInBackground(Integer... params) {
            final String url = "http://211.229.100.53:8080" + "/event";

            // Accept header "application/json" 을 명시하여 JSON 데이터를 리턴받길 원한다고 설정해 줍니다.
            HttpHeaders requestHeaders = new HttpHeaders();
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(acceptableMediaTypes);

            // Rest Template 객체에서 사용할 reqeustEntity 를 위에서 정의한 request Header 를 사용해서 생성합니다.
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);


            // 서버와의 통신을 담당할 RestTemplate 객체를 생성합니다.
            RestTemplate restTemplate = new RestTemplate();
            // MappingJacksonHttpMessageConverter 를 통해서 결과값인 JSON 데이터를 Message 데이터로 변환할 수 있습니다.
            MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
            restTemplate.getMessageConverters().add(converter);

            // HTTP GET 요청을 수행합니다.
            ResponseEntity<Item_event[]> responseEntity = null;
            try {
                responseEntity  = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item_event[].class);
            }
            catch (RestClientException restException) {
                // examine the restException
            }

            // 결과값을 받아옵니다.
            return responseEntity.getBody();
        }

        @Override
        protected void onPostExecute(Item_event[] result) {

            // 결과값을 찍어줍니다.
            showResult(result);
        }

        public void showResult( Item_event[] model){
            //

            if(model.length==0) {
                dbManager.insert_init_event();
            }
            else {
                for(int i=0; i<model.length; i++) {
                    dbManager.insert_add_event(model[i].getMid(),model[i].getMname(),model[i].getMf_price(),model[i].getMl_price());
                    Log.i("", "HTTP_TEST1 : " + model[i].getMid() + " " + model[i].getMname() + " " + model[i].getMf_price() + " " + model[i].getMl_price() + model.length );
                }
            }

        }
    }

    class HttpGetTask_Item_goods extends AsyncTask<Integer, Void, Item_goods[]> {
        @Override
        protected Item_goods[] doInBackground(Integer... params) {
            final String url = "http://211.229.100.53:8080" + "/goods";

            // Accept header "application/json" 을 명시하여 JSON 데이터를 리턴받길 원한다고 설정해 줍니다.
            HttpHeaders requestHeaders = new HttpHeaders();
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(acceptableMediaTypes);

            // Rest Template 객체에서 사용할 reqeustEntity 를 위에서 정의한 request Header 를 사용해서 생성합니다.
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);


            // 서버와의 통신을 담당할 RestTemplate 객체를 생성합니다.
            RestTemplate restTemplate = new RestTemplate();
            // MappingJacksonHttpMessageConverter 를 통해서 결과값인 JSON 데이터를 Message 데이터로 변환할 수 있습니다.
            MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
            restTemplate.getMessageConverters().add(converter);

            // HTTP GET 요청을 수행합니다.
            ResponseEntity<Item_goods[]> responseEntity = null;
            try {
                responseEntity  = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Item_goods[].class);
            }
            catch (RestClientException restException) {
                // examine the restException
            }

            // 결과값을 받아옵니다.
            return responseEntity.getBody();
        }

        @Override
        protected void onPostExecute(Item_goods[] result) {

            // 결과값을 찍어줍니다.
            showResult(result);
        }

        public void showResult( Item_goods[] model){

            if(model.length==0) {
                dbManager.insert_init_goods();
            }
            else {
                for(int i=0; i<model.length; i++) {
                    dbManager.insert_add_goods(model[i].getMid(),model[i].getMname(),model[i].getmWeight(),model[i].getmPrice(),model[i].getMc_id());

                    Log.i("", "HTTP_TEST2 : " + model[i].getMid() + " " +  model[i].getMname() + " " +  model[i].getmWeight() + " " +  model[i].getmPrice() + " " +  model[i].getMc_id() + model.length);
                }

                Intent intent_main = new Intent(MainActivity.this, MainActivity.class);
                intent_main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_main);
            }

        }
    }

}