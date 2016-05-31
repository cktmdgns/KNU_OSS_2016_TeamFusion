package mobilelecture.cdp12_app;

/**
 * Created by Administrator on 2016-05-09.
 */
public class Listview_item_tab3 {
    private String mdate;
    private String mbody;
    private String  mEA;
    private String mprice;

    Listview_item_tab3(String date, String body, String EA, String price) {
        mdate = date;
        mbody = body;
        mEA = EA;
        mprice = price;
    }

    public String getDate(){
        return mdate;
    }
    public String getBody(){
        return mbody;
    }
    public String getEA(){
        return mEA;
    }
    public String getPrice(){
        return mprice;
    }

}
