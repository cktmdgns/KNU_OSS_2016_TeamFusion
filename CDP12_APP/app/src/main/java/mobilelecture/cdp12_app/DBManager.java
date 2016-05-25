package mobilelecture.cdp12_app;

/**
 * Created by user on 2015-12-16.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
                "id INTEGER, name TEXT, location TEXT, s_id INTEGER , x1 INTEGER, y1 INTEGER, xlen INTEGER, ylen INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS GOODS " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, weight TEXT, price TEXT, c_id INTEGER);");

        db.execSQL("CREATE TABLE IF NOT EXISTS EVENT " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, f_price INTEGER , l_price INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS CART " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, name TEXT, EA INTEGER, C_NAME TEXT, G_LOC INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS PATHVERTEX " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "C_NAME TEXT, V_ID INTEGER );");

        db.execSQL("CREATE TABLE IF NOT EXISTS VERTEX " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "id INTEGER, x1 INTEGER, y1 INTEGER );");

        db.execSQL( "INSERT INTO SHOP (id, name, location) VALUES (1,'홈플러스','황금')" );



        // 채소/건나물 : (0,55) / (30,160)
        // 조리/주방용품/그릇 : (470,75) / (500,180)

        // 생선/해산물1 : (40,1) / (140,20)
        // 정육/계란1 : (144,1) / (240,20)
        // 헤어/세안/바디 : (270,1) / (340,20)
        // 랩/호일고무장갑 : (345,1) / (420,20)
        // 세제/화장지 : (420,1) / (500,55)

        // 생선/해산물2 : (50,30) / (130,45)
        // 정육/계란2 : (150,30) / (230,45)
        // 행사1 : (265,30) / (315,42)
        // 행사2 : (325,30) / (375,42)

        // 두부/김치 : (50,55) / (130,85)
        // 반찬 : (150,60) / (230,85)
        // 냉동식품/아이스크림 : (265,50) / (307,90)
        // 커피/차 : (316,50) / (357,90)
        // 과자 : (365,50) / (410,90)

        // 과일 : (50,95) / (120,130)
        // 음료 : (135,95) / (160,130)
        // 유제품 : (175,95) / (200,130)
        // 주류 : (215,95) / (240,130)
        // 행사3 : (265,105) / (315,115)
        // 행사4 : (325,105) / (375,115)
        // 행사5 : (385,105) / (435,115)
        // 행사6 : (265,125) / (315,130)
        // 행사7 : (320,125) / (375,130)
        // 행사8 : (380,125) / (430,130)

        // 김/미역/건어 : (55,150) / (135,160)
        // 쌀,잡곡 : (180,150) / (225,180)
        // 식용류/조미료/밀가루 : (135,150) / (275,180)
        // 쌀,잡곡 : (135,150) / (275,180)
        // 라면 : (290,150) / (330,180)
        // 통조림/캔 : (340,150) / (380,180)
        // 장류/케찹/소스 : (390,150) / (430,180)


        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (1,'생선/해산1', 1001, 40, 1, 100, 5 )" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (2,'정육/계란1', 1001, 144, 1, 95, 5 )" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (3,'생선/해산2', 1001, 50, 30, 80, 15)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (4,'정육/계란2', 1001, 150, 30, 80, 15)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (5,'과일', 1001, 50, 95, 70, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (6,'채소/건나물', 1001, 1, 55, 30, 105 )" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (7,'쌀/잡곡', 1001, 185, 150, 40, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (8,'김/미역/건어', 1001, 55, 150, 80, 10)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (9,'유제품/냉장음료', 1001, 175, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (10,'두부/김치', 1001, 50, 55, 80, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (11,'냉동식품/아이스크림', 1001, 265, 50, 42, 40)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (12,'음료/생수', 1001, 135, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (13,'커피/차', 1001, 316, 50, 40, 40)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (14,'과자', 1001, 365, 50, 45, 40)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (15,'라면', 1001, 290, 150, 40, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (16,'식용유/조미료/밀가루', 1001, 235, 150, 40, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (17,'통조림/캔', 1001, 340, 150, 40, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (18,'장류/케찹/소스', 1001, 390, 150, 40, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (19,'세제/화장지', 1001, 420, 1, 80, 55)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (20,'헤어/세안/바디', 1001, 270, 1, 70, 20)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (21,'랩/호일/고무장갑', 1001, 345, 1, 75, 20)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (22,'조리/주방용품/그릇', 1001, 470, 75, 30, 105)" );

        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (23,'반찬', 1001, 150, 60, 80, 25)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (24,'주류', 1001, 215, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (25,'행사1', 1001, 265, 30, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (26,'행사2', 1001, 325, 30, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (27,'행사3', 1001, 265, 105, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (28,'행사4', 1001, 325, 105, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (29,'행사5', 1001, 385, 105, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (30,'행사6', 1001, 265, 125, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (31,'행사7', 1001, 325, 125, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (32,'행사8', 1001, 380, 125, 50, 12)" );



        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (1,'해동고등어','400','1590', 1)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (2,'해동오징어', '250', '1590', 1)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (22,'돼지삼겹살', '100', '1090', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (23,'돼지목심', '100', '1090', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (24,'돼지앞다리', '100', '2290', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (25,'냉동닭가슴살', '1000', '5990', 2)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (42,'고당도오렌지', '110', '890', 5)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (43,'자몽', '110', '990', 5)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (44,'냉동블루베리', '500', '6000', 5)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (45,'딸기', '500', '4990', 5)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (63,'햇감자', '100', '590', 6)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (64,'애호박', '260', '1990', 6)" );
        db.execSQL( "INSERT INTO GOODS (id, name, weight, price, c_id) VALUES (65,'제주무', '1500', '1490', 6)" );
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (66,'파프리카', '50', '1290', 6)" );




        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (1,'해동고등어', '1590', '1000')" );
        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (2,'해동오징어', '1090', '700')" );
        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (3,'돼지삼겹살', '1090', '700')" );
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (6,'냉동닭가슴살', '5990', '4000')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (7,'고당도오렌지', '890', '700')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (10,'딸기', '4990', '3500')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (11,'햇감자', '590', '400')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (14,'파프리카', '1290', '800')");


        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (1,'파프리카', 3, '채소/건나물', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (2,'고당도오렌지', 2, '과일', 2)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (3,'딸기', 1, '과일', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (4,'햇감자', 2, '채소/건나물', 5)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (5,'자몽', 4, '과일', 3)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (6,'돼지목심', 5, '정육/계란1', 4)");



        //vertex
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (1, 30, 22)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (2, 60, 22)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (3, 95, 22)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (4, 128, 22)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (5, 169, 22)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (6, 212, 22)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (7, 255, 22)");

        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (8, 30, 38)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (9, 60, 38)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (10, 95, 38)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (11, 128, 38)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (12, 169, 36)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (13, 210, 35)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (14, 240, 35)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (15, 253, 35)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (16, 273, 35)");

        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (17, 30, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (18, 60, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (19, 87, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (20, 97, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (21, 113, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (22, 128, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (23, 139, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (24, 166, 60)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (25, 190, 66)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (26, 208, 66)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (27, 223, 66)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (28, 240, 66)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (29, 248, 66)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (30, 275, 66)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (31, 294, 66)");

        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (32, 30, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (33, 60, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (34, 85, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (35, 100, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (36, 115, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (37, 128, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (38, 139, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (39, 152, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (40, 169, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (41, 187, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (42, 212, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (43, 222, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (44, 233, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (45, 252, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (46, 270, 95)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (47, 294, 95)");

        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (48, 170, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (49, 192, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (50, 212, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (51, 231, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (52, 250, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (53, 270, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (54, 294, 80)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (55, 307, 83)");

        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (56, 103, 115)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (57, 118, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (58, 138, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (59, 153, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (60, 170, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (61, 188, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (62, 206, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (63, 222, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (64, 239, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (65, 255, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (66, 272, 124)");
        db.execSQL("INSERT INTO VERTEX (id, x1, y1) VALUES (67, 298, 124)");


        // vertex
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산1', 1)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산1', 2)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산1', 3)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란1', 3)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란1', 4)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란1', 5)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('헤어/세안/바디', 5)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('헤어/세안/바디', 6)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('랩/호일/고무장갑', 7)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산2', 1)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산2', 2)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산2', 3)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산2', 8)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산2', 9)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('생선/해산2', 10)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란2', 3)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란2', 4)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란2', 5)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란2', 8)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란2', 9)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('정육/계란2', 10)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사1', 5)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사1', 6)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사1', 12)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사1', 13)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사2', 6)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사2', 7)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사2', 13)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사2', 14)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사2', 15)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('세제/화장지', 16)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('두부/김치', 8)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('두부/김치', 9)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('두부/김치', 17)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('두부/김치', 18)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('두부/김치', 19)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('두부/김치', 20)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 10)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 11)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 12)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 20)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 21)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 22)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 23)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('반찬', 24)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('냉동식품/아이스크림', 12)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('냉동식품/아이스크림', 13)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('냉동식품/아이스크림', 24)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('냉동식품/아이스크림', 25)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('냉동식품/아이스크림', 26)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('커피/차', 13)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('커피/차', 14)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('커피/차', 26)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('커피/차', 27)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('커피/차', 28)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 14)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 15)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 16)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 28)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 29)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 30)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 31)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과자', 32)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과일', 17)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과일', 18)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과일', 19)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과일', 32)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과일', 33)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('과일', 34)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('음료/생수', 19)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('음료/생수', 20)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('음료/생수', 21)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('음료/생수', 34)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('음료/생수', 35)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('음료/생수', 36)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('유제품/냉장음료', 21)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('유제품/냉장음료', 22)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('유제품/냉장음료', 23)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('유제품/냉장음료', 36)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('유제품/냉장음료', 37)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('유제품/냉장음료', 38)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('주류', 23)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('주류', 24)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('주류', 38)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('주류', 39)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('주류', 40)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사3', 24)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사3', 25)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사3', 26)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사3', 48)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사3', 49)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사3', 50)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 26)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 27)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 28)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 29)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 50)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 51)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사4', 52)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사5', 29)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사5', 30)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사5', 31)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사5', 52)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사5', 53)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사5', 54)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사6', 48)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사6', 49)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사6', 50)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사6', 40)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사6', 41)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사6', 42)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 50)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 51)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 52)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 42)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 43)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 44)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사7', 45)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사8', 52)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사8', 53)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사8', 54)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사8', 45)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사8', 46)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('행사8', 47)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('채소/건나물', 8)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('채소/건나물', 17)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('채소/건나물', 32)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('김/미역/건어', 32)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('김/미역/건어', 33)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('김/미역/건어', 34)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('김/미역/건어', 35)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('김/미역/건어', 56)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 36)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 37)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 38)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 39)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 57)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 58)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('쌀/잡곡', 59)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('식용유/조미료/밀가루', 39)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('식용유/조미료/밀가루', 40)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('식용유/조미료/밀가루', 41)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('식용유/조미료/밀가루', 59)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('식용유/조미료/밀가루', 60)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('식용유/조미료/밀가루', 61)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('라면', 41)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('라면', 42)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('라면', 43)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('라면', 61)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('라면', 62)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('라면', 63)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('통조림/캔', 43)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('통조림/캔', 44)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('통조림/캔', 45)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('통조림/캔', 63)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('통조림/캔', 64)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('통조림/캔', 65)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('장류/케찹/소스', 45)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('장류/케찹/소스', 46)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('장류/케찹/소스', 47)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('장류/케찹/소스', 65)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('장류/케찹/소스', 66)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('장류/케찹/소스', 67)");

        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('조리/주방용품/그릇', 31)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('조리/주방용품/그릇', 47)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('조리/주방용품/그릇', 54)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('조리/주방용품/그릇', 55)");
        db.execSQL("INSERT INTO PATHVERTEX (C_NAME, V_ID) VALUES ('조리/주방용품/그릇', 67)");

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

    public ArrayList<String> select_Search(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM GOODS where name like  '%" + goodsName + "%';", null);
        while(c.moveToNext()) {
            str = c.getString(0);
            arrlist.add(str);
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Classcification(int Cid) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM GOODS where c_id = '" + Cid + "' ;", null);
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

    public String select_GoodsID_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "";

        Cursor c = db.rawQuery("SELECT id FROM GOODS where name like '%" + goodsName + "%';", null);
        while(c.moveToNext()) {
            str = String.valueOf(c.getInt(0));
        }
        db.close();
        return str;
    }

    public ArrayList<String> select_Cart() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        String str="";
        Cursor c = db.rawQuery("SELECT name FROM CART order by C_NAME;", null);
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

    public ArrayList<String> select_Cart_forMap() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT name FROM CART order by C_NAME;", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Cart_forMap_byname(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT C_NAME, G_LOC FROM CART WHERE name = '" +  name + "';", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
            arrlist.add(String.valueOf(c.getInt(1)));
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

    public ArrayList<ConnerPosition> select_conners() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ConnerPosition> arrlist = new ArrayList<ConnerPosition>();

        Cursor c = db.rawQuery("SELECT x1, y1 FROM VERTEX;", null);
        while(c.moveToNext()) {
            ConnerPosition temp_connerposition = new ConnerPosition();
            temp_connerposition.pic_x = c.getInt(0);
            temp_connerposition.pic_y = c.getInt(1);
            arrlist.add(temp_connerposition);
        }
        db.close();
        return arrlist;
    }


    public void insert_cart(String name, int EA, String C_NAME, int G_LOC) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select EA from CART where name = '" + name + "'", null);
        String grd="";
        while(cursor.moveToNext()) {
            if (!cursor.getString(0).equals(""))
                grd =  String.valueOf(cursor.getInt(0));
        }
        if(grd.equals(""))
            sql = "INSERT INTO CART (name, EA, C_NAME, G_LOC) VALUES ('" + name + "' , " + EA + ", '" + C_NAME + "', " + G_LOC + " );";
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


    public ArrayList<Integer> select_CornerPosition_byConnerName(String CornerName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> arrlist = new ArrayList<Integer>();

        Cursor c = db.rawQuery("SELECT x1, y1, xlen, ylen FROM CORNER where name = '" + CornerName + "';", null);
        while(c.moveToNext()) {
            arrlist.add(Integer.valueOf(c.getString(0)));
            arrlist.add(Integer.valueOf(c.getString(1)));
            arrlist.add(Integer.valueOf(c.getString(2)));
            arrlist.add(Integer.valueOf(c.getString(3)));
        }
        db.close();
        return arrlist;
    }

    public String select_IsThereInform() {
        SQLiteDatabase db = getWritableDatabase();
        String return_str = "";

        Cursor c = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE name='INFORM'", null);
        while(c.moveToNext()) {
            return_str = c.getString(0);
        }
        db.close();
        return return_str;
    }

    public void insert_inform(String zender, String age) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("CREATE TABLE IF NOT EXISTS INFORM " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "ZENDER TEXT, AGE TEXT );");

        sql = "INSERT INTO INFORM (ZENDER, AGE) VALUES ('" + zender + "' , '" + age + "' );";

        db.execSQL(sql);
        db.close();
    }
}