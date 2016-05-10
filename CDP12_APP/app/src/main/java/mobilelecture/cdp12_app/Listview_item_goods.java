package mobilelecture.cdp12_app;

/**
 * Created by Administrator on 2016-05-09.
 */
public class Listview_item_goods {
    private String mMenuIcon;
    private String mMenuName;
    private String mWeight;
    private String mPrice;

    Listview_item_goods(String menuIcon, String menuName, String weight, String price) {
        mMenuIcon = menuIcon;
        mMenuName = menuName;
        mWeight = weight;
        mPrice = price;
    }

    public String getMenuIcon(){
        return mMenuIcon;
    }

    public String getMenuName() {
        return mMenuName;
    }

    public String  getWeight() {
        return mWeight;
    }

    public String  getPrice() {
        return mPrice;
    }

}
