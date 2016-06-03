package mobilelecture.cdp12_app;

import java.math.MathContext;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by user on 2016-03-29.
 */
public class CustomAdapter_listview_goods extends BaseAdapter implements OnClickListener {

    private DBManager dbManager = null;
    // Activity에서 가져온 객체정보를 저장할 변수
    private Listview_item_goods mUser;
    private Context mContext;
    private String default_drawable_path = "android.resource://mobilelecture.cdp12_app/drawable/";

    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<Listview_item_goods> mUserData;

    public CustomAdapter_listview_goods(Context context) {
        super();
        mContext = context;
        mUserData = new ArrayList<Listview_item_goods>();
    }


    @Override
    /**
     * @return 아이템의 총 개수를 반환
     */
    public int getCount() {
        // TODO Auto-generated method stub
        return mUserData.size();
    }

    @Override
    /**
     * @return 선택된 아이템을 반환
     */
    public Listview_item_goods getItem(int position) {
        // TODO Auto-generated method stub
        return mUserData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    /**
     * getView
     *
     * @param position - 현재 몇 번째로 아이템이 추가되고 있는지 정보를 갖고 있다.
     * @param convertView - 현재 사용되고 있는 어떤 레이아웃을 가지고 있는지 정보를 갖고 있다.
     * @param parent - 현재 뷰의 부모를 지칭하지만 특별히 사용되지는 않는다.
     * @return 리스트 아이템이 저장된 convertView
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;
        final PersonViewHolder viewHolder;

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.custom_listview_goods, null);

            viewHolder = new PersonViewHolder();

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            viewHolder.imgMenuIcon = (ImageView) v.findViewById(R.id.imageView_menuicon_goods);
            viewHolder.menu_name = (TextView) v.findViewById(R.id.textView_menuname_goods);
            viewHolder.weight = (TextView) v.findViewById(R.id.textView_weight_goods);
            viewHolder.price = (TextView) v.findViewById(R.id.textView_price_goods);
            viewHolder.addcart = (ImageView) v.findViewById(R.id.imageButton_addcart_goods);
            viewHolder.searchcart = (ImageView) v.findViewById(R.id.imageButton_search_goods);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (PersonViewHolder) v.getTag();
        }

        viewHolder.addcart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getRootView().getContext()).setTitle("확인").setMessage("추가하시겠습니까?")
                        .setNegativeButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbManager = new DBManager(mContext, "test.db", null, 1);
                                String corner_name = dbManager.select_CName_byInt(Integer.valueOf(dbManager.select_Goods_byname(viewHolder.menu_name.getText().toString()).get(2)));
                                dbManager.insert_cart(viewHolder.menu_name.getText().toString(), 1, corner_name, 1);
                                //((MainActivity)v.getRootView().getContext()).resetCartListView();
                                Toast.makeText (mContext, "추가되었습니다", Toast.LENGTH_SHORT).show();
                            }
                        }).setPositiveButton("아니오",null).show();

            }

        });

        viewHolder.searchcart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getRootView().getContext()).setTitle("확인").setMessage("상품 위치 검색")
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbManager = new DBManager(mContext, "test.db", null, 1);
                                String corner_name = dbManager.select_CName_byInt(Integer.valueOf(dbManager.select_Goods_byname(viewHolder.menu_name.getText().toString()).get(2)));

                                /*
                                Intent intent_search_corner = new Intent(mContext,GoodsMapSearch.class);
                                intent_search_corner.putExtra("GoodsName", "" + viewHolder.menu_name.getText().toString());
                                intent_search_corner.putExtra("ConerName", "" + corner_name);
                                intent_search_corner.putExtra("TYPE", "" + 1);
                                //Log.i("","Custom_goods_test : " + viewHolder.menu_name.getText().toString() + corner_name );
                                intent_search_corner.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(intent_search_corner);
                                */
                                Intent intent_search_corner = new Intent(mContext, RecoRangingActivity.class);
                                intent_search_corner.putExtra("GoodsName", "" + viewHolder.menu_name.getText().toString());
                                intent_search_corner.putExtra("ConerName", "" + corner_name);
                                intent_search_corner.putExtra("TYPE", "" + 1);
                                //Log.i("","Custom_goods_test : " + viewHolder.menu_name.getText().toString() + corner_name );
                                intent_search_corner.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent_search_corner.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(intent_search_corner);
                            }
                        }).setPositiveButton("취소",null).show();

            }

        });

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = getItem(position);



        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            if (mUser.getMenuIcon() != null) {
                Glide.with(mContext).load(Uri.parse( default_drawable_path + mUser.getMenuIcon() )).into(viewHolder.imgMenuIcon);
                Glide.with(mContext).load(Uri.parse( default_drawable_path + "cart_con")).into(viewHolder.addcart);
                Glide.with(mContext).load(Uri.parse( default_drawable_path + "glass_con")).into(viewHolder.searchcart);
            }
            viewHolder. menu_name.setText(mUser.getMenuName());
            viewHolder.price.setText(mUser.getWeight());
            viewHolder.weight.setText(mUser.getPrice());
        }
        // 완성된 아이템 뷰를 반환합니다.
        return v;
    }

    public class PersonViewHolder {
        // ListView 내부 View들을 가르킬 변수들
        private ImageView imgMenuIcon;
        private TextView menu_name;
        private TextView weight;
        private TextView price;
        private ImageView addcart;
        private ImageView searchcart;
    }







    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(Listview_item_goods user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub


    }
}