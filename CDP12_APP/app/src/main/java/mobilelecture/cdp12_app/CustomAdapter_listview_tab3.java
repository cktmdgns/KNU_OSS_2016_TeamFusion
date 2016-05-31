package mobilelecture.cdp12_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by user on 2016-03-29.
 */
public class CustomAdapter_listview_tab3 extends BaseAdapter implements OnClickListener {

    private DBManager dbManager = null;
    // Activity에서 가져온 객체정보를 저장할 변수
    private Listview_item_tab3 mUser;
    private Context mContext;

    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<Listview_item_tab3> mUserData;

    public CustomAdapter_listview_tab3(Context context) {
        super();
        mContext = context;
        mUserData = new ArrayList<Listview_item_tab3>();
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
    public Listview_item_tab3 getItem(int position) {
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
                    .inflate(R.layout.custom_listview_tab3, null);

            viewHolder = new PersonViewHolder();

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            viewHolder.date = (TextView) v.findViewById(R.id.textView_date_tab3);
            viewHolder.body = (TextView) v.findViewById(R.id.textView_body_tab3);
            viewHolder.EA = (TextView) v.findViewById(R.id.textView_EA_tab3);
            viewHolder.price = (TextView) v.findViewById(R.id.textView_price_tab3);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (PersonViewHolder) v.getTag();
        }

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = getItem(position);

        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            viewHolder.date.setText(mUser.getDate());
            viewHolder.body.setText(mUser.getBody());
            viewHolder.EA.setText(mUser.getEA());
            viewHolder.price.setText(mUser.getPrice());
        }
        // 완성된 아이템 뷰를 반환합니다.
        return v;
    }

    public class PersonViewHolder {
        // ListView 내부 View들을 가르킬 변수들
        private TextView date;
        private TextView body;
        private TextView EA;
        private TextView price;

    }



    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(Listview_item_tab3 user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub


    }
}