package mobilelecture.cdp12_app;

import android.graphics.drawable.Drawable;

/**
 * Created by user on 2016-03-29.
 */
public class Listview_item_cart {
    private String mMenuIcon;
    private String mWhere;
    private String mMenuName;
    private String mCount;
    private String mWeight;
    private String mPrice;

    Listview_item_cart(String menuIcon, String where, String menuName, String count, String weight, String price) {
        mMenuIcon = menuIcon;
        mWhere = where;
        mMenuName = menuName;
        mCount = count;
        mWeight = weight;
        mPrice = price;
    }

    public String getMenuIcon(){
        return mMenuIcon;
    }

    public String  getWhere() {
        return mWhere;
    }

    public String getMenuName() {
        return mMenuName;
    }

    public String  getCount() {
        return mCount;
    }

    public String  getWeight() {
        return mWeight;
    }

    public String  getPrice() {
        return mPrice;
    }

}