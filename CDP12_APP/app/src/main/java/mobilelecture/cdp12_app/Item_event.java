package mobilelecture.cdp12_app;

public class Item_event {

	private long id;
	
	private String mid;
    private String mname;
    private String mf_price;
    private String ml_price;

    
    protected Item_event() {
		
	}
    
	public Item_event(String id, String name, String f_price, String l_price) {
        mid = id;
        mname = name;
        mf_price = f_price;
        ml_price = l_price;
    }

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMf_price() {
		return mf_price;
	}

	public void setMf_price(String mf_price) {
		this.mf_price = mf_price;
	}

	public String getMl_price() {
		return ml_price;
	}

	public void setMl_price(String ml_price) {
		this.ml_price = ml_price;
	}

}