package mobilelecture.cdp12_app;

import android.graphics.drawable.Drawable;

/**
 * Created by user on 2016-03-29.
 */
public class Listview_item_event {
    private Drawable mMenuIcon;
    private String mMenuName;
    private String mPrice1_1;
    private String mPrice1_2;

    Listview_item_event(Drawable menuIcon, String menuName, String price1_1, String price1_2) {
        mMenuIcon = menuIcon;
        mMenuName = menuName;
        mPrice1_1 = price1_1;
        mPrice1_2 = price1_2;
    }

    public Drawable getMenuIcon(){
        return mMenuIcon;
    }

    public String getMenuName() {
        return mMenuName;
    }

    public String  getPrice1_1() {
        return mPrice1_1;
    }

    public String  getPrice1_2() {
        return mPrice1_2;
    }
}