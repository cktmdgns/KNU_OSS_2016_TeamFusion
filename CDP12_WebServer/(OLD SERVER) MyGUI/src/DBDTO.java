
import java.util.Scanner;

public class DBDTO {

	private int cornerselect;
	private int idinsert;
	private String nameinsert;
	private int heavyinsert;
	private int priceinsert;
	private int locationinsert;
	private int c_idinsert;
	private String deletename;
	private String updatekind;
	private String updatename;
	private int numberupdate;

	public int getcorner() {
		return cornerselect;
	}

	public void setcorner(int corenr) {
		this.cornerselect = corenr;
	}
	
	public int getid() {
		return idinsert;
	}

	public void setid(int id) {
		this.idinsert = id;
	}
	
	public String getname() {
		return nameinsert;
	}

	public void setname(String name) {
		this.nameinsert = name;
	}

	public int getheavy() {
		return heavyinsert;
	}

	public void setheavy(int heavy) {
		this.heavyinsert = heavy;
	}

	public int getprice() {
		return priceinsert;
	}

	public void setprice(int price) {
		this.priceinsert = price;
	}

	public int getlocation() {
		return locationinsert;
	}

	public void setlocation(int location) {
		this.locationinsert = location;
	}

	public int getc_id() {
		return c_idinsert;
	}

	public void setc_id(int c_id) {
		this.c_idinsert = c_id;
	}
	

	public String getdename() {
		return deletename;
	}

	public void setdename(String dename) {
		this.deletename = dename;
	}
	
	public String getkind() {
		return updatekind;
	}

	public void setkind(String kind) {
		this.updatekind = kind;
	}

	public String getupname() {
		return updatename;
	}

	public void setupname(String upname) {
		this.updatename = upname;
	}
	
	public int getnumber() {
		return numberupdate;
	}

	public void setnumber(int number) {
		this.numberupdate = number;
	}
}
