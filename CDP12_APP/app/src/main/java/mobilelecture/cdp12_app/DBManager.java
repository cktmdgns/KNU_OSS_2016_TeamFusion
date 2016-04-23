package mobilelecture.cdp12_app;

/**
 * Created by user on 2015-12-16.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private String sql;

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);

        db.execSQL("CREATE TABLE IF NOT EXISTS SHOP " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, location TEXT );");

        db.execSQL("CREATE TABLE IF NOT EXISTS CORNER " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, location TEXT, s_id INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS GOODS " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, weight TEXT, price TEXT, c_id INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS EVENT " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, f_price INTEGER , l_price INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS CART " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, EA INTEGER );");


        db.execSQL( "INSERT INTO SHOP (id, name, location) VALUES (1,'홈플러스','황금')" );

        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (1,'생선/해산', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (2,'정육/계란', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (3,'과일', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (4,'채소/건나물', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (5,'쌀/잡곡', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (6,'김/미역/건어', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (7,'유제품/냉장음료', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (8,'두부/김치', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (9,'냉동식품/아이스크림', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (10,'음료/생수', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (11,'커피/차', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (12,'과자', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (13,'라면', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (14,'식용유/조미료/밀가루', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (15,'통조림/캔', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (16,'장류/케찹/소스', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (17,'세제/화장지', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (18,'헤어/세안/바디', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (19,'랩/호일/고무장갑', 1001)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location) VALUES (20,'조리/주방용품/그릇', 1001)" );

        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (1,'해동고등어', '400', '1590', 1)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (2,'해동오징어', '250', '1590', 1)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (3,'돼지삼겹살', '100', '1090', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (4,'돼지목심', '100', '1090', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (5,'부채살(호주)', '100', '2290', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (6,'냉동닭가슴살', '1000', '5990', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (7,'고당도오렌지', '250', '890', 3)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (8,'자몽', '380', '990', 3)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (9,'냉동블루베리', '500', '6000', 3)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (10,'딸기', '500', '4990', 3)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (11,'햇감자', '100', '590', 4)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (12,'애호박', '260', '1990', 4)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (13,'제주무', '1500', '1490', 4)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (14,'파프리카', '200', '1290', 4)" );



        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (1,'해동고등어', '1590', '1000')" );
        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (2,'돼지삼겹살', '1090', '700')" );
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (3,'냉동닭가슴살', '5990', '4000')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (4,'고당도오렌지', '890', '700')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (5,'딸기', '4990', '3500')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (6,'햇감자', '590', '400')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (7,'파프리카', '1290', '800')");


        db.execSQL("INSERT INTO CART (id,name, EA) VALUES (1,'파프리카', 3)");
        db.execSQL("INSERT INTO CART (id,name, EA) VALUES (2,'고당도오렌지', 2)");
        db.execSQL("INSERT INTO CART (id,name, EA) VALUES (3,'딸기', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA) VALUES (4,'햇감자', 2)");
        db.execSQL("INSERT INTO CART (id,name, EA) VALUES (5,'자몽', 4)");
        db.execSQL("INSERT INTO CART (id,name, EA) VALUES (6,'돼지목심', 5)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public ArrayList<String> select_Goods() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM GOODS;", null);
        while(c.moveToNext()) {
            str = c.getString(0);
            arrlist.add(str);
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Goods_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT weight, price, c_id FROM GOODS where name = '" + goodsName + "';", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
            arrlist.add(c.getString(1));
            arrlist.add(c.getString(2));
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Event() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM EVENT;", null);
        while(c.moveToNext()) {
            str = c.getString(0);
            arrlist.add(str);
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Event_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT f_price, l_price FROM EVENT where name = '" + goodsName + "';", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
            arrlist.add(c.getString(1));
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Cart() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM CART order by name;", null);
        while(c.moveToNext()) {
            str = c.getString(0);
            arrlist.add(str);
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Cart_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT weight, price, c_id FROM GOODS where name = '" + goodsName + "';", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
            arrlist.add(c.getString(1));
            arrlist.add(c.getString(2));
        }
        db.close();
        return arrlist;
    }

    public String select_CartEA_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "";

        Cursor c = db.rawQuery("SELECT EA FROM CART where name = '" + goodsName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0);
        }
        db.close();
        return str;
    }

    public String select_CName_byInt(int CName_int) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "";

        Cursor c = db.rawQuery("SELECT NAME FROM CORNER where id = '" + CName_int + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0);
        }
        db.close();
        return str;
    }



    public void insert_cart(String name, int EA) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select EA from CART where name = '" + name + "'", null);
        String grd="";
        while(cursor.moveToNext()) {
            if (!cursor.getString(0).equals(""))
                grd =  String.valueOf(cursor.getInt(0));
        }
        if(grd.equals(""))
            sql = "INSERT INTO CART (name, EA) VALUES ('" + name + "' , " + EA + " );";
        else
            sql = "UPDATE CART SET EA=EA+1 where name='" + name + "'";
        db.execSQL(sql);
        db.close();
    }

    public void delete_cart_byname(String name) {
        SQLiteDatabase db = getWritableDatabase();
        sql = "DELETE FROM CART WHERE name = '" + name  + "';";
        db.execSQL(sql);
        db.close();
    }


    /*
    public void insert_byname(String name, String filename, String category,
                              String season, String temp, String weather, String favor) {
        SQLiteDatabase db = getWritableDatabase();
        sql= "INSERT INTO clothes_list (name, filename, category, season, temp, weather, favor)" +
                " VALUES ('" + name + "', '" + filename +  "', '"  + category +  "', '" +
                season +  "', '" + temp +  "', '" + weather +  "', '" +  favor +  "');";
        db.execSQL(sql);
        db.close();
    }

    public void delete_clothesName(String name){
        SQLiteDatabase db = getWritableDatabase();
        sql = "DELETE FROM clothes_list WHERE name = '" + name  + "';";
        db.execSQL(sql);
        db.close();
    }

    public void update_byname(String name, String filename, String category,
                              String season, String temp, String weather, String favor) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "";

        sql= "UPDATE clothes_list SET filename = '" + filename + "', category = '" + category + "' " +
                ", season = '" + season + "' , temp = '" + temp + "' , weather = '" + weather + "' " +
                ", favor = '" + favor + "'  where name = '" + name + "';";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<String> select_clothesName() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM clothes_list order by name;", null);
        while(c.moveToNext()) {
            str = c.getString(0);
            arrlist.add(str);
        }
        db.close();
        return arrlist;
    }

    public String select_fileName(String clothesName) {
        SQLiteDatabase db = getWritableDatabase();

        String str="";
        Cursor c = db.rawQuery("SELECT filename FROM clothes_list where name = '" + clothesName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
        }
        db.close();
        return str;
    }

    public String select_category_byname(String clothesName) {
        SQLiteDatabase db = getWritableDatabase();
        String str="";
        Cursor c = db.rawQuery("SELECT category FROM clothes_list where name = '" + clothesName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
        }
        db.close();
        return str;
    }

    public String select_season_byname(String clothesName) {
        SQLiteDatabase db = getWritableDatabase();
        String str="";
        Cursor c = db.rawQuery("SELECT season FROM clothes_list where name = '" + clothesName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
        }
        db.close();
        return str;
    }

    public String select_temp_byname(String clothesName) {
        SQLiteDatabase db = getWritableDatabase();

        String str="";
        Cursor c = db.rawQuery("SELECT temp FROM clothes_list where name = '" + clothesName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
        }
        db.close();
        return str;
    }

    public String select_weather_byname(String clothesName) {
        SQLiteDatabase db = getWritableDatabase();

        String str="";
        Cursor c = db.rawQuery("SELECT weather FROM clothes_list where name = '" + clothesName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
        }
        db.close();
        return str;
    }

    public String select_favor_byname(String clothesName) {
        SQLiteDatabase db = getWritableDatabase();
        String str="";
        Cursor c = db.rawQuery("SELECT favor FROM clothes_list where name = '" + clothesName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
        }
        db.close();
        return str;
    }

    public ArrayList<String> select_search_wardrobe(String category, String season, String clothesName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();
        String str = "";
        Boolean blank_check = false;

        if (category.equals("ㅡㅡㅡㅡ")) {
            if (season.equals("ㅡㅡㅡㅡ")) {
                if(clothesName.equals("")) {
                    blank_check = true;
                }
                else {
                    str = "SELECT name FROM clothes_list where name LIKE '%" + clothesName + "%' order by name;";
                }
            } else {
                if(clothesName.equals("")) {
                    str = "SELECT name FROM clothes_list where season = '" + season + "' order by name;";
                }
                else {
                    str = "SELECT name FROM clothes_list where name LIKE '%" + clothesName + "%' and season = '" + season + "' order by name;";
                }
            }
        }
        else if (season.equals("ㅡㅡㅡㅡ")) {
            if (category.equals("ㅡㅡㅡㅡ")) {
                if(clothesName.equals("")) {
                    blank_check = true;
                }
                else {
                    str = "SELECT name FROM clothes_list where name LIKE '%" + clothesName + "%' order by name;";
                }
            } else {
                if(clothesName.equals("")) {
                    str = "SELECT name FROM clothes_list where category = '" + category + "' order by name;";
                }
                else {
                    str = "SELECT name FROM clothes_list where name LIKE '%" + clothesName + "%' and category = '" + category + "' order by name;";
                }
            }
        }
        else {
            if (clothesName.equals("")) {
                str = "SELECT name FROM clothes_list where category = '" + category + "' and season = '" + season + "' order by name;";
            }
            else {
                str = "SELECT name FROM clothes_list where name LIKE '%" + clothesName + "%' and category = '" + category + "' and season = '" + season + "' order by name;";
            }
        }

        if(blank_check == true) {
            str = "SELECT name FROM clothes_list order by name;";
        }

        Cursor c = db.rawQuery(str, null);
        while(c.moveToNext()) {
            str = c.getString(0) ;
            arrlist.add(str);
        }

        db.close();
        return arrlist;
    }
    */
}