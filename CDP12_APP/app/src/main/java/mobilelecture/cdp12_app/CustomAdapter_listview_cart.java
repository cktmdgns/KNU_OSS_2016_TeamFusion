package mobilelecture.cdp12_app;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2016-03-29.
 */
public class CustomAdapter_listview_cart extends BaseAdapter implements OnClickListener {

    // Activity에서 가져온 객체정보를 저장할 변수
    private Listview_item_cart mUser;
    private Context mContext;

    // ListView 내부 View들을 가르킬 변수들
    private ImageView imgUserIcon;
    private TextView menu_name;
    private TextView price1_1;
    private TextView price1_2;
    private ImageButton btnSend;

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

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.custom_listview_shop1, null);

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            imgUserIcon = (ImageView) v.findViewById(R.id.user_icon);
            menu_name = (TextView) v.findViewById(R.id.menu_name1);
            price1_1 = (TextView) v.findViewById(R.id.price1_1);
            price1_1.setPaintFlags(price1_1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            price1_2 = (TextView) v.findViewById(R.id.price1_2);
            btnSend = (ImageButton) v.findViewById(R.id.btn_send);
        }

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = getItem(position);

        // Tag를 이용하여 데이터와 뷰를 묶습니다.
        btnSend.setTag(mUser);

        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            if (mUser.getMenuIcon() != null) {
                imgUserIcon.setImageDrawable(mUser.getMenuIcon());
            }
            menu_name.setText(mUser.getMenuName());
            price1_1.setText(mUser.getPrice1_1());
            price1_2.setText(mUser.getPrice1_2());
            btnSend.setOnClickListener(this);
        }
        // 완성된 아이템 뷰를 반환합니다.
        return v;
    }

    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(Listview_item_cart user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        // Tag를 이용하여 Data를 가져옵니다.
        Listview_item_cart clickItem = (Listview_item_cart) v.getTag();

        switch (v.getId()) {
            case R.id.btn_send:
                Toast.makeText(mContext, clickItem.getPrice1_2(),
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}