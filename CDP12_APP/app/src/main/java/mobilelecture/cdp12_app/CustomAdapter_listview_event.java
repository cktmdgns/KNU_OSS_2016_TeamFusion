package mobilelecture.cdp12_app;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
public class CustomAdapter_listview_event extends BaseAdapter implements OnClickListener {

    private DBManager dbManager = null;

    // Activity에서 가져온 객체정보를 저장할 변수
    private Listview_item_event mUser;
    private Context mContext;
    private String default_drawable_path = "android.resource://mobilelecture.cdp12_app/drawable/";

    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<Listview_item_event> mUserData;

    public CustomAdapter_listview_event(Context context) {
        super();
        mContext = context;
        mUserData = new ArrayList<Listview_item_event>();
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
    public Listview_item_event getItem(int position) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;
        final PersonViewHolder viewHolder;

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.custom_listview_event, null);

            viewHolder = new PersonViewHolder();

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            viewHolder.imgMenuIcon = (ImageView) v.findViewById(R.id.imageView_menuicon_event);
            viewHolder.alramIcon = (ImageView) v.findViewById(R.id.imageView_alramicon_event);
            viewHolder.menu_name = (TextView) v.findViewById(R.id.textView_menuname_event);
            viewHolder.price1 = (TextView) v.findViewById(R.id.textView_price1_event);
            viewHolder.price2 = (TextView) v.findViewById(R.id.textView_price2_event);
            viewHolder.addcart = (ImageButton) v.findViewById(R.id.imageButton_addcart_event);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (PersonViewHolder) v.getTag();
        }

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = getItem(position);


        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            if (mUser.getMenuIcon() != null) {
                Glide.with(mContext).load(Uri.parse( default_drawable_path + mUser.getMenuIcon() )).into(viewHolder.imgMenuIcon);
                Glide.with(mContext).load(Uri.parse( default_drawable_path + "alram_con")).into(viewHolder.alramIcon);
            }
            viewHolder. menu_name.setText(mUser.getMenuName());
            viewHolder.price1.setText(mUser.getPrice1_1());
            viewHolder.price2.setText(mUser.getPrice1_2());
            viewHolder.addcart.setOnClickListener(this);
        }
        // 완성된 아이템 뷰를 반환합니다.
        return v;
    }


    public class PersonViewHolder {
        // ListView 내부 View들을 가르킬 변수들
        private ImageView imgMenuIcon;
        private ImageView alramIcon;
        private TextView menu_name;
        private TextView price1;
        private TextView price2;
        private ImageButton addcart;
    }







    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(Listview_item_event user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        // Tag를 이용하여 Data를 가져옵니다.
        /*

        PersonViewHolder viewHolder = (PersonViewHolder) v.getTag();

        switch (v.getId()) {
            case R.id.imageButton_addcart_event:
                dbManager = new DBManager(mContext, "test.db", null, 1);
                dbManager.insert_cart(viewHolder.menu_name.getText().toString(),1);


//                Toast.makeText(mContext, clickItem.getPrice1_2(),
//                        Toast.LENGTH_SHORT).show();
                break;
        }
        */
    }
}