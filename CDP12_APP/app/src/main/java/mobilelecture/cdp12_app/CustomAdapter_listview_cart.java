package mobilelecture.cdp12_app;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by user on 2016-03-29.
 */
public class CustomAdapter_listview_cart extends BaseAdapter implements OnClickListener {

    // Activity에서 가져온 객체정보를 저장할 변수
    private Listview_item_cart mUser;
    private Context mContext;
    private String default_drawable_path = "android.resource://mobilelecture.cdp12_app/drawable/";

    private boolean[] isCheckedConfrim;



    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<Listview_item_cart> mUserData;

    public CustomAdapter_listview_cart(Context context) {
        super();
        mContext = context;
        mUserData = new ArrayList<Listview_item_cart>();
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
    public Listview_item_cart getItem(int position) {
        // TODO Auto-generated method stub
        return mUserData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setCheckCount() {
        // TODO Auto-generated method stub
        isCheckedConfrim = new boolean[getCount()];
    }

    public boolean getChecked(int position) {
        return isCheckedConfrim[position];
    }

    public void setOpposition(int position) {
        isCheckedConfrim[position] = !isCheckedConfrim[position];
    }

    public void removeItem(int position) {
        for (int i=position; i<isCheckedConfrim.length - 1; i++) {
            isCheckedConfrim[position] = isCheckedConfrim[position+1];
        }
        Log.i("CustomAdapter","" + mUserData.get(position).getMenuName());

        mUserData.remove(position);
        notifyDataSetChanged();

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
        //android:background="?android:attr/activatedBackgroundIndicator"  추가시 리스트뷰 선택
        // TODO Auto-generated method stub
        View v = convertView;
        final PersonViewHolder viewHolder;

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.custom_listview_cart, null);

            viewHolder = new PersonViewHolder();

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            viewHolder.imgMenuIcon = (ImageView) v.findViewById(R.id.imageView_menuicon_cart);
            viewHolder.where = (TextView) v.findViewById(R.id.textView_where_cart);
            viewHolder.menu_name = (TextView) v.findViewById(R.id.textView_menuname_cart);
            viewHolder.count = (TextView) v.findViewById(R.id.textView_count_cart);
            viewHolder.weight = (TextView) v.findViewById(R.id.textView_weight_cart);
            viewHolder.price = (TextView) v.findViewById(R.id.textView_price_cart);
            viewHolder.checkbox_cart = (CheckBox) v.findViewById(R.id.checkbox_cart);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (PersonViewHolder) v.getTag();
        }
        viewHolder.checkbox_cart.setChecked(isCheckedConfrim[position]);
        viewHolder.checkbox_cart.setFocusable(false);
        viewHolder.checkbox_cart.setClickable(false);

        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.checkbox_cart = (CheckBox) v.findViewById(R.id.checkbox_cart);
                Log.i("Addpter_cart", "click Posion = " + position);
                setOpposition(position);
                viewHolder.checkbox_cart.setChecked(isCheckedConfrim[position]);

            }
        });

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = getItem(position);

        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            if (mUser.getMenuIcon() != null) {
                Glide.with(mContext).load(Uri.parse( default_drawable_path + mUser.getMenuIcon())).into(viewHolder.imgMenuIcon);
            }
            viewHolder.where.setText(mUser.getWhere());
            viewHolder.menu_name.setText(mUser.getMenuName());
            viewHolder.count.setText(mUser.getCount());
            viewHolder.weight.setText(mUser.getWeight());
            viewHolder.price.setText(mUser.getPrice());
        }

        //checkbox_cart.setChecked(((ListView)parent).isItemChecked(position));
        return v;
    }

    public String getEA(int position) {
        mUser = getItem(position);
        return mUser.getCount();
    }

    public class PersonViewHolder {
        // ListView 내부 View들을 가르킬 변수들
        private ImageView imgMenuIcon;
        private TextView where;
        private TextView menu_name;
        private TextView count;
        private TextView weight;
        private TextView price;
        private CheckBox checkbox_cart;
    }




    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(Listview_item_cart user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}