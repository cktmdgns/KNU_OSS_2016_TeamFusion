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

        db.execSQL("CREATE TABLE IF NOT EXISTS SHOPING_LIST " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "date DATE, year TEXT, month TEXT, day TEXT, name TEXT, price TEXT, EA TEXT );");

        db.execSQL("CREATE TABLE IF NOT EXISTS BEACON_LIST " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "SSN TEXT, MINOR TEXT, NAME TEXT);");

        db.execSQL( "INSERT INTO SHOP (id, name, location) VALUES (1,'홈플러스','황금')" );

        // 가계부 용 DB
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 04월 17일', '2016', '04', '17','동원미니돈가스', '4580원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 04월 17일', '2016', '04', '17','롯데햄떡갈비', '4980원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 04월 17일', '2016', '04', '17','칠성사이다', '3470원', '2개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 04월 17일', '2016', '04', '17','알로에농장', '3450원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 04월 17일', '2016', '04', '17','오렌지', '1970원', '2개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 14일', '2016', '05', '14','동원미니돈가스', '4580원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 14일', '2016', '05', '14','스카시포도', '2240원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 14일', '2016', '05', '14','초코송이', '1920원', '2개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 14일', '2016', '05', '14','새우깡', '1700원', '2개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 22일', '2016', '05', '22','진짬뽕', '5480원', '4개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 22일', '2016', '05', '22','동원미니돈가스', '4580원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 22일', '2016', '05', '22','포도쨈', '4600원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 29일', '2016', '05', '22','롯데햄떡갈비', '4980원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 29일', '2016', '05', '29','위생백', '1000원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 29일', '2016', '05', '29','동원미니돈가스', '4580원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 29일', '2016', '05', '29','오렌지', '990원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 29일', '2016', '05', '29','딸기', '4190원', '2개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 30일', '2016', '05', '30','돼지 목살', '5000원', '4개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 05월 31일', '2016', '05', '31','블루베리원', '990원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 06월 01일', '2016', '06', '01','남양맛있는우유', '2140원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 06월 01일', '2016', '06', '01','요거트딸기', '2500원', '1개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 06월 02일', '2016', '06', '02','배', '2990원', '2개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 06월 02일', '2016', '06', '02','오뚜기중화볶음밥', '4480원', '4개')" );
        db.execSQL( "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ('2016년 06월 02일', '2016', '06', '02','고향김치손만두', '6980원', '2개')" );



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
        //db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (9,'유제품', 1001, 175, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (9,'유제품', 1001, 175, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (10,'두부/김치', 1001, 50, 55, 80, 30)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (11,'냉동식품', 1001, 265, 50, 42, 40)" );
        //db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (12,'음료', 1001, 135, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (12,'음료', 1001, 135, 95, 25, 35)" );
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

        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (23,'아이스크림', 1001, 150, 60, 80, 25)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (24,'주류', 1001, 215, 95, 25, 35)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (25,'행사1', 1001, 265, 30, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (26,'행사2', 1001, 325, 30, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (27,'행사3', 1001, 265, 105, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (28,'행사4', 1001, 325, 105, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (29,'행사5', 1001, 385, 105, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (30,'행사6', 1001, 265, 125, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (31,'행사7', 1001, 325, 125, 50, 12)" );
        db.execSQL( "INSERT INTO CORNER (id, name, location, x1, y1, xlen, ylen) VALUES (32,'행사8', 1001, 380, 125, 50, 12)" );

        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (1,'해동고등어','400','1590',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (2,'해동오징어','250','1590',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (3,'해동꽁치','120','390',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (4,'해동제주갈치','300','6990',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (5,'왕전복','80','5490',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (6,'활바지락','500','2990',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (7,'멍게','150','3990',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (8,'해삼','150','3990',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (9,'훈제연어','300','9990',1)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (10,'칵테일새우','300','12900',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (11,'생랍스타','1000','34900',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (12,'생대게','700','39900',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (13,'제주삼치살','500','11900',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (14,'제주고등어살','500','11900',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (15,'해동쭈꾸미','100','990',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (16,'논우렁살','110','3190',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (17,'해동명태알','100','1290',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (18,'해동새우살','100','2690',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (19,'자반고등어','400','2490',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (20,'활모시','200','2590',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (21,'낱개초밥','30','490',3)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (22,'돼지삼겹살','100','2320',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (23,'돼지목심','100','2400',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (24,'돼지앞다리','100','1360',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (25,'냉동닭가슴살','1000','6490',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (26,'갈비살(미국우육)','100','2990',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (27,'닭볽음탕용닭','1200','6890',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (28,'양념소불고기','600','10990',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (29,'돼지뒷다리불고기용','600','8590',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (30,'돼지뒷다리찜용','300','5590',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (31,'한우등심양념불고기','400','14990',2)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (32,'냉동우곰탕','500','2990',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (33,'백숙용토종닭','1050','8990',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (34,'냉동닭다리','1000','5990',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (35,'LA식꽃갈비(호주)','1600','42900',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (36,'찜갈비(호주)','1600','39900',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (37,'생닭11호','1000','5990',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (38,'생닭9호','800','5530',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (39,'친환경대란(계란)','1560','3000',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (40,'한우등심1+등급','100','6500',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (41,'돼지안심','100','1090',4)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (42,'고당도오렌지','250','890',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (43,'자몽','380','1490',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (44,'냉동블루베리','1000','9800',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (45,'딸기','500','4990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (46,'토마토','1000','5990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (47,'사과','1800','8490',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (48,'골드키위','800','9990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (49,'밀감','1500','12900',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (50,'참다래','700','5990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (51,'천혜향','1500','14900',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (52,'페루애플망고','550','5990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (53,'한라봉','1500','12900',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (54,'바나나','2100','3990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (55,'수박4호','4500','14900',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (56,'청포도','1200','7990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (57,'배','630','2990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (58,'레몬','120','890',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (59,'아보카도','240','2990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (60,'냉동딸기','500','6990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (61,'건망고','100','3990',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (62,'금귤','500','4490',5)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (63,'햇감자','100','590',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (64,'애호박','260','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (65,'제주무','1500','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (66,'파프리카','200','1490',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (67,'맛타리버섯','180','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (68,'대파','300','2990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (69,'깐마늘','100','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (70,'깻잎','100','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (71,'다다기오이','400','3990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (72,'적상추','200','1490',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (73,'콜라비','350','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (74,'아욱','150','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (75,'호박고구마','700','3990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (76,'단호박','800','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (77,'초벌부추','150','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (78,'샐러드','190','3490',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (79,'쑥갓','150','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (80,'근대','150','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (81,'아스파라거스','150','4990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (82,'새송이버섯','400','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (83,'팽이버섯','150','490',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (84,'시금치','250','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (85,'표고버섯','400','2800',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (86,'시금치','200','2400',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (87,'햇당근','100','290',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (88,'양파','250','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (89,'양배추','100','490',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (90,'청양고추','170','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (91,'양상추','450','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (92,'레드믹스','80','990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (93,'가지','300','1290',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (94,'오이고추','250','1990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (95,'절단대파','300','3990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (96,'브로컬리','400','2990',6)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (97,'찹쌀','800','3990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (98,'찰현미','4000','15900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (99,'상주전통곶감','500','9990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (100,'고시히까리','20000','49900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (101,'이천미','10000','35900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (102,'건포도','200','1990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (103,'THEPLUS좋은쌀','10000','24900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (104,'귀리쌀','3800','7990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (105,'무농약쌀','10000','32900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (106,'혼합잡곡15곡','800','7990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (107,'부드러운찰흑미','800','4990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (108,'건크랜베리','200','2990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (109,'깐밤','200','5990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (110,'캘리포니아호두','200','7390',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (111,'현미','4000','13900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (112,'이천미','20000','66900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (113,'서리태','500','6990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (114,'청원생명쌀','10000','30900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (115,'철원미','10000','30900',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (116,'찰보리쌀','800','3990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (117,'적두','500','6990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (118,'코코아아몬드','200','5990',7)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (119,'진미오징어','250','9990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (120,'볶음용멸치','200','9980',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (121,'김자반','70','3990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (122,'코코넛스낵김','25','2990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (123,'아몬드스낵김','25','2990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (124,'양반들기름김','150','4790',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (125,'김밥김','20','1790',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (126,'황태채','100','5990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (127,'쇠고기육포','150','9990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (128,'치즈육포','50','3990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (129,'통영멸치','200','8790',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (130,'조미쥐치포','150','4500',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (131,'건조오징어','280','7450',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (132,'쥐포채','150','4500',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (133,'건한치','200','9950',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (134,'옛날자른미역','50','1990',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (135,'옛날미역','100','3190',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (136,'사각다시마','100','2790',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (137,'꽃새우','100','8790',8)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (138,'서울멸균우유','1000','2500',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (139,'서울저지방치즈','270','4900',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (140,'휘핑크림','250','3000',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (141,'남양앳홈오렌지','950','3500',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (142,'모짜렐라치즈','270','6600',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (143,'남양엣홈포도','950','3500',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (144,'매일에스프레소라떼','250','1650',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (145,'매일슈거에스프레소','250','1650',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (146,'매일모카프레소','250','1650',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (147,'매일카라멜딥프레소','250','1650',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (148,'남양맛있는우유','2000','4280',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (149,'비피더스','1800','3980',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (150,'요거트생크림','664','2500',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (151,'요거트딸기','664','2500',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (152,'피자치즈','1000','10000',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (153,'남양저지방우유','1800','4080',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (154,'필라델피아크림치즈','200','3700',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (155,'아침에주스오렌지','1800','3980',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (156,'빙그레요플레10입','850','4980',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (157,'빙그레바나나우유6입','1440','5980',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (158,'동원 고칼슘우유','1800','3980',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (159,'프레스티지까망베르','125','4500',9)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (160,'오징어젓','270','8500',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (161,'주부초밥왕','320','3480',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (162,'아삭포기김치씨제이','1500','9900',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (163,'김밥맛살','505','3980',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (164,'김밥단무지','400','1990',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (165,'맛있는맛김치대상종가','1900','11900',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (166,'순두부찌개양념','420','3480',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (167,'꼬마김밥세트','103','3590',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (168,'양념깻잎','120','1380',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (169,'유정란메추리알','300','2700',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (170,'맛쌈무와사비맛','350','1280',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (171,'된장찌개양념','420','3480',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (172,'고들빼기','150','2990',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (173,'쫄깃무말랭이','120','1380',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (174,'연근조림','120','1380',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (175,'명란젓갈','220','9580',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (176,'맛있는포기김치대상종가','3700','22800',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (177,'창란젓','270','6580',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (178,'양념듬뿍쌈장','500','3980',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (179,'참치김치볶음','300','3990',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (180,'양반아삭김치','1000','7780',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (181,'콩나물좋은상품','600','900',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (182,'콩이가득두부','300','1000',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (183,'풀무원콩나물','200','1350',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (184,'풀무원순두부','350','1700',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (185,'풀무원소가찌개두부','300','1250',10)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (186,'하겐다즈그린티','473','6980',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (187,'풀무원핫도그','600','7480',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (188,'하겐다즈커피','473','6980',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (189,'하겐다즈클래식밀크','473','6980',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (190,'고메치킨순살크리스피','550','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (191,'밀당의고수김말이','700','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (192,'오뚜기불닭철판볶음밥','450','4480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (193,'오뚜기쇠고기볶음밥','450','4480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (194,'오뚜기새우볶음밥','450','4480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (195,'오뚜기중화볶음밥','450','4480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (196,'갈릭버터스테이크','300','9990',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (197,'고메치킨핫스파이시','550','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (198,'계절밥상시래기나물밥','433','5980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (199,'비비고취나물밥','433','5980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (200,'비비고도톰동그랑땡','850','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (201,'동원미니돈가스','1000','7580',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (202,'비비고언양식바싹불고기','460','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (203,'한성오징어동그랑땡','600','5980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (204,'동원수제돈까스','480','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (205,'동원프리미엄돈까스','1200','5950',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (206,'씨제이너비아니','560','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (207,'롯데햄떡갈비','720','9980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (208,'오뚜기갈비산적','800','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (209,'한성흰살생선까스','900','8000',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (210,'한성해물경단','900','7480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (211,'동원제주돼지완자','900','8480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (212,'비비고왕교자','980','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (213,'비비고김치왕교자','940','9780',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (214,'해태고양만두','1440','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (215,'감자떡만두','1200','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (216,'생야채와돼지고기물만두','800','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (217,'고향김치손만두','1100','6980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (218,'동원제주돼지왕만두','938','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (219,'풀무원납작지짐만두','1000','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (220,'백설군만두','1040','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (221,'풀무원갈비만두','800','8480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (222,'평양왕만두','1400','9500',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (223,'해태고향만두','1350','8480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (224,'개성감자만두','1100','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (225,'알감자만두','1100','7980',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (226,'해태고향만두','1650','9480',11)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (227,'코카콜라캔','1110','4320',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (228,'칠성사이다','1140','4000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (229,'게토레이레몬','1500','2500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (230,'델몬트오렌지','1800','4280',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (231,'환타오렌지캔','1110','4320',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (232,'검은콩두유','3900','14900',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (233,'맥심TOP마스터라떼','1200','5500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (234,'현미아침햇살','1500','3450',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (235,'게토레이블루볼트','1500','2500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (236,'솔의눈','1440','4280',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (237,'검은콩고칼슘두유','3040','16900',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (238,'알로에농장','1500','3450',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (239,'미닌메이드자몽','1200','3800',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (240,'델몬트매실','1500','2880',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (241,'토마토농장','1500','3450',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (242,'TOP스위트아메리카','1200','5500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (243,'밀키스','500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (244,'초찬탄산수라임','500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (245,'초청탄산수자몽','500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (246,'썬키스트자몽소다','350','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (247,'포카리스웨트','340','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (248,'코코팜복숭아','300','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (249,'옥수수수염차','500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (250,'가을우엉차','500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (251,'스카시오렌지','1500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (252,'스카시포도','1500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (253,'맥콜','1500','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (254,'데모소다애플캔','1500','2000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (255,'칸타타아메리카노','275','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (256,'조지아고티카아로마라떼','270','1000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (257,'팹시콜라','1140','2000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (258,'갈아만든배','1500','2000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (259,'홈플러스샘물','12000','2000',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (260,'핫식스','750','1650',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (261,'암바사','1500','1500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (262,'배지밀토틀러','3040','7480',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (263,'워터샘물','2000','580',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (264,'홈플러스콜라','1500','500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (265,'홈플러스사이다','1500','500',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (266,'삼다수','10000','7600',12)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (267,'맥심모카골드믹스','2760','20900',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (268,'맥심화이트골드믹스','2691','20900',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (269,'아카시아꿀','500','13000',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (270,'맥스웰하우스오리지날','2124','10280',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (271,'네스카페수프리모커피믹스','234','4000',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (272,'현미녹차','150','4600',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (273,'카누미니마일드로스트','90','1900',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (274,'메밀차','60','3800',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (275,'구수한동굴레차','120','8400',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (276,'동글레차','60','5740',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (277,'오뚜기꿀유자차','1000','5380',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (278,'복음자리유자차','620','6560',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (279,'맥심모카골드블랙믹스','270','12800',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (280,'순보리차','300','2100',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (281,'야생화꿀','500','12500',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (282,'마테차','40','6900',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (283,'꿀생강차','1000','6900',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (284,'옥수수차','300','2600',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (285,'생강차','225','3800',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (286,'누룽지차','60','4100',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (287,'호두아몬드율무차','270','4500',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (288,'쌍화차','225','4000',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (289,'루이보스보리차','75','3500',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (290,'꿀모과차','1000','6180',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (291,'립톤아이스티','1120','17800',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (292,'헤이즐넛','60','3600',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (293,'녹차라떼','130','3950',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (294,'대추차','225','4000',13)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (295,'몽쉘크림케이크','384','4780',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (296,'초코홈런볼컵','51','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (297,'몽쉘카카오케이크','384','4780',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (298,'프링글스오리지널','110','2700',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (299,'크리스피감자칩오리지널','125','500',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (300,'촉촉한초코칩','120','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (301,'킨더조이보이','120','1260',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (302,'초코파이','468','3800',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (303,'롯데빈츠','76','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (304,'크리스피감자칩어니언','125','500',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (305,'프링글스양파맛','110','2700',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (306,'칙촉오리지널','180','38000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (307,'버터링','223','3100',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (308,'롯데샌드','300','2880',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (309,'빠다코코낫','156','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (310,'칸쵸','216','2880',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (311,'엄마손파이','254','4780',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (312,'조리퐁','186','2380',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (313,'프링클스버터카라멜','110','2700',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (314,'뻥이요','270','2000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (315,'참ing치즈크림','135','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (316,'롯데씨리얼','84','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (317,'뽀또크림치즈','368','4240',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (318,'몽쉘허니유자블렌딩','360','4780',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (319,'프링글스치즈맛','110','2700',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (320,'버터와플','316','3000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (321,'후렌치파이사과','256','3680',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (322,'땅콩카라멜','400','4240',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (323,'쌀과자','320','2480',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (324,'후레쉬베리','336','2980',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (325,'허니통통','130','2380',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (326,'사브레','105','1000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (327,'로아커웨하스바닐라','125','2980',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (328,'미왕고소한쌀과자','300','2480',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (329,'초코송이','72','960',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (330,'아이비크래커','270','2000',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (331,'치즈샌드','240','3980',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (332,'미니핫브레이크','574','10600',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (333,'참붕어빵','232','2980',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (334,'고래밥볶음양념','56','960',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (335,'새우깡','90','850',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (336,'로아커가데나핑거믹스','500','13480',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (337,'초코하임','284','3300',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (338,'맛동산','75','960',14)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (339,'팔도남자라면','115','300',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (340,'육개장사발면','86','660',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (341,'진짬뽕','520','5480',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (342,'김치사발면','86','660',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (343,'볶음짜짜로니','700','2000',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (344,'신라면','600','3150',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (345,'안성탕면','625','2850',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (346,'참깨라면','575','4500',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (347,'팔도비빔면','650','3100',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (348,'얼큰한너구리','600','3450',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (349,'짜파게티버먹','70','660',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (350,'진라면소컵순한맛','390','2950',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (351,'그대로짜장','200','1580',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (352,'백미밥','1260','3000',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (353,'그대로카레약간매운맛','200','1580',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (354,'그대로카레매운맛','200','1580',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (355,'그래도카레순한맛','200','1580',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (356,'참깨라면큰컵','110','840',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (357,'튀김우동큰사발','111','850',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (358,'올리브짜파게티','700','3650',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (359,'신라면큰사발','114','800',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (360,'새우탕큰사발','115','850',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (361,'왕뚜껑','110','840',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (362,'소문난라면','550','1500',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (363,'스낵면','540','2000',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (364,'육계장큰사발면','110','850',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (365,'햇반현미밥','630','3000',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (366,'햇반실속','2520','9480',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (367,'콘푸로스트','600','6100',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (368,'진라면매운맛','600','2750',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (369,'책스초코','570','6980',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (370,'김치왕뚜껑','110','840',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (371,'햇반흑미밥','630','3000',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (372,'불닭볶음면큰컵','105','980',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (373,'순한너구리','600','3480',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (374,'짜왕','536','4980',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (375,'삼양라면','600','3080',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (376,'맛있는밥','2100','7480',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (377,'아몬두푸레이크','630','4900',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (378,'초코볼','570','4100',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (379,'사리곰탕큰사발','111','850',15)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (380,'해표양조식초','1800','1000',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (381,'식용유','1500','5950',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (382,'진한참기름','500','9900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (383,'카놀라유','900','5950',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (384,'백설포도씨유','900','10900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (385,'오뚜기양조식초','1800','1700',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (386,'하얀설탕','1000','1590',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (387,'큐원갈색설탕','1000','1950',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (388,'미향','900','3280',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (389,'포도씨유','900','10900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (390,'사과식초','900','1900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (391,'포도식초','800','3700',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (392,'부침가루','1000','2450',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (393,'쁘띠챌미초석류','900','9900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (394,'쁘띠챌미초청포도','900','9900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (395,'요리올리고당','700','2980',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (396,'중력분','1000','1400',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (397,'신박력분','1000','1570',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (398,'백설갈색설탕','10000','2060',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (399,'순후추캔','100','3750',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (400,'쁘띠챌미초레몬유자','900','9900',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (401,'쇠고기다시다명품골드','96','3200',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (402,'미원맛소금','250','950',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (403,'순후추','45','260',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (404,'핫케익가루','500','2250',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (405,'큐원하얀설탕','1000','1630',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (406,'찹쌀호떡믹스','400','2000',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (407,'멸치액젓','800','3780',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (408,'감자전분맛','400','3300',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (409,'빵가루','450','2250',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (410,'옛날물엿','1200','3250',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (411,'쇠고기다시다','300','5400',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (412,'양조식초','900','1050',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (413,'굵은소금','10000','2390',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (414,'부침가루홈플','1000','1700',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (415,'현미식초홈플','1800','2520',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (416,'배사과식초','900','2500',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (417,'허브맛솔트마늘양파','52','2700',16)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (418,'마일드참치','210','1480',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (419,'꽁치캔','400','1480',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (420,'스팸클래시마일드','1200','11980',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (421,'스위트콘','340','1280',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (422,'살코키참치고추참치','600','5980',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (423,'땅콩크런치','462','3000',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (424,'땅콩크라미','462','3000',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (425,'딸기잼','640','6680',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (426,'동원살코기참치38호','300','5300',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (427,'로스팜앤네이쳐','340','5380',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (428,'황금복숭아','410','2180',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (429,'사과쨈','620','6270',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (430,'고추참치','300','5300',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (431,'슬라이스파인애플','836','3980',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (432,'자연산골뱅이','140','3970',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (433,'황도슬라이스','400','1400',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (434,'안심팜','600','5380',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (435,'땅콩버터청크','482','6380',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (436,'캔고등어','400','1980',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (437,'런천미트','200','1750',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (438,'후루츠칵테일','850','4180',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (439,'볶음김치','120','3150',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (440,'포도쨈','370','4600',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (441,'그린자이안트블넷스위트','340','1780',17)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (442,'몽고진간장','1800','1000',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (443,'새콤달콤고추장','1050','3000',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (444,'사계절쌈장','1000','6850',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (445,'스파게티소스미트','685','4750',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (446,'케찹','500','2000',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (447,'골드마요네즈','300','2450',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (448,'케찹','300','1300',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (449,'청정원후레쉬마요네즈','300','2150',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (450,'초코스프레드','368','3000',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (451,'재래식된장','1000','5900',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (452,'허니머스타드소스','320','2300',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (453,'스파게티소스마늘양파','685','4750',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (454,'스파게티소스토마토','685','4750',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (455,'로제스파게티소스','600','3800',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (456,'토마토파스타소스','455','3780',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (457,'버섯크림스파게티소스','250','3200',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (458,'태양초고추장','2000','9900',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (459,'그대로끓여먹는된장찌개','450','2600',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (460,'돈까스소스','415','1650',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (461,'이금기팬더굴소스','510','3250',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (462,'양조간장','1800','11300',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (463,'아라비아따스파게티소스','600','3800',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (464,'국간장','930','3820',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (465,'가쓰오부시국수장국','360','2880',18)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (466,'피존핑크로즈향','2800','3300',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (467,'유한펑크린','2000','3640',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (468,'피죤엘로우미모사향','2800','3300',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (469,'유한락스레귤러','1500','2910',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (470,'피존블루피앙카향','2800','3300',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (471,'베이킹소다액체세제','3000','9900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (472,'천연베이킹소다','2000','3450',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (473,'퍼플화이트티와릴리향','2000','8900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (474,'싱크대배수관클리너','680','6200',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (475,'배수관세정제','2000','2800',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (476,'이지오프뱅변기세정제','200','2600',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (477,'에어윅아로마겔라벤더','210','6500',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (478,'홈스타욕실용세제','500','3000',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (479,'유한락스후로랄','1000','2400',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (480,'테크세탁세제','4000','21800',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (481,'유리세정제','660','1980',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (482,'꽃담초섬유유연제','1300','9900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (483,'액츠파워젤액체세제','3000','7900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (484,'천연성분구연산','1000','3450',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (485,'물먹는하마옷장용','320','9900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (486,'세탁조세정제','500','2500',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (487,'암앤해머베이킹소다','1500','6900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (488,'유한락스후레쉬','2000','4140',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (489,'설거지주방세제','1200','4900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (490,'옥시크린','2800','19500',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (491,'점보비누','450','1900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (492,'살균비누','230','1750',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (493,'슈가버블주방세제','1250','5400',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (494,'물티슈','276','1000',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (495,'좋은느낌울트라날개','341','9900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (496,'화이트미용티슈','300','7980',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (497,'키친타올','500','3800',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (498,'뽑아쓰는키친타올','400','4700',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (499,'크리넥스미용티슈','600','9900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (500,'부드러운3겹화장지','900','16900',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (501,'무형광화장지','810','16800',19)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (502,'엘리스틴데미지샴푸','780','13000',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (503,'엘라스틴모이스처샴푸','780','13000',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (504,'케라시스퍼퓸러블리샴푸','600','13500',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (505,'케라시스퍼퓸러블리린스','600','13500',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (506,'핸드워시레몬향','200','4500',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (507,'크로스이펙트칫솔','200','8900',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (508,'데톨핸드워시스킨케어','250','3400',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (509,'리스테린액내추럴그린티','750','9200',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (510,'2080패밀리케어치약','450','3950',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (511,'데톨향균비누데일리케어','400','7500',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (512,'해피바스바디워시','900','9500',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (513,'온더바디로즈바디워시','900','9500',20)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (514,'메디안포레스트치약','300','6900',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (515,'종이컵','192','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (516,'부탄가스','880','4500',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (517,'다회용접시','100','2000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (518,'나무젓가락','200','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (519,'위생백','250','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (520,'위생장갑','100','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (521,'크린랩','350','1170',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (522,'일회용스푼','100','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (523,'종이그릇','150','940',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (524,'고무장갑','80','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (525,'반짝이원형수세미','100','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (526,'마른행주','100','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (527,'쿠킹오일','500','4800',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (528,'면봉','100','1000',21)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (529,'조리도구','50','990',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (530,'스탠양수냄비','850','12900',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (531,'다이아몬드후라이팬','700','9900',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (532,'뉴바이오직사각900','160','2700',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (533,'뉴바이오직사각2400','240','2700',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (534,'PET물병','100','1290',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (535,'글라스락원형','150','2300',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (536,'마린프라이팬','500','20700',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (537,'락앤락페트물병','150','1090',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (538,'비스프리스포츠물병','150','3990',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (539,'에코물병','200','5890',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (540,'밥공기','300','9900',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (541,'주방가위','250','3990',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (542,'과일집과도','200','1990',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (543,'티스푼5set','250','5490',22)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (544,'참이슬','360','1200',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (545,'참소주','360','1200',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (546,'좋은데이석류','360','1250',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (547,'좋은데이블루베리','360','1250',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (548,'순하리','360','1250',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (549,'카스캔맥주','500','1600',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (550,'카스병맥주','500','1100',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (551,'카스피처맥주','1000','3200',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (552,'하이트캔맥주','500','1600',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (553,'하이트병맥주','500','1100',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (554,'하이트피처맥주','1000','3200',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (555,'국순당막걸리','750','1500',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (556,'오비맥주','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (557,'아사히','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (558,'하이네켄','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (559,'버드와이저','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (560,'칭따오','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (561,'기네스','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (562,'밀러','355','3300',24)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (563,'월드콘','800','5000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (564,'메로나','480','3000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (565,'누가바','378','3000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (566,'쌍쌍바','402','3000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (567,'옥동자','420','3000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (568,'구구콘','800','5000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (569,'쿠키앤크림아이스크림','900','6900',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (570,'붕어싸만코','750','5000',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (571,'빙빙바','75','500',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (572,'더위사냥','140','500',23)");
        db.execSQL("INSERT INTO GOODS (id, name, weight, price,c_id) VALUES (573,'뽕따소다','130','500',23)");




        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (1,'해동고등어', '1590', '1000')" );
        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (2,'해동오징어', '1090', '700')" );
        db.execSQL( "INSERT INTO EVENT (id, name, f_price, l_price) VALUES (3,'돼지삼겹살', '1090', '700')" );
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (6,'냉동닭가슴살', '5990', '4000')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (7,'고당도오렌지', '890', '700')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (10,'딸기', '4990', '3500')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (11,'햇감자', '590', '400')");
        db.execSQL("INSERT INTO EVENT (id, name, f_price, l_price) VALUES (14,'파프리카', '1290', '800')");


        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (1,'파프리카', 3, '채소/건나물', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (2,'고당도오렌지', 2, '과일', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (3,'딸기', 1, '과일', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (4,'햇감자', 2, '채소/건나물', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (5,'자몽', 4, '과일', 1)");
        db.execSQL("INSERT INTO CART (id,name, EA, C_NAME, G_LOC) VALUES (6,'돼지목심', 5, '정육/계란1', 1)");

        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116640', '16640', '생선/해산1')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116641', '16641', '정육/계란1')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116642', '16642', '생선/해산2')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116643', '16643', '정육/계란2')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116644', '16644', '과일')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116645', '16645', '채소/건나물')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116646', '16646', '쌀/잡곡')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116647', '16647', '유제품')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116648', '16648', '아이스크림')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116649', '16649', '음료')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116650', '16650', '과자')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50116651', '16651', '라면')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50114915', '14915', '장류/케찹/소스')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50114916', '14916', '세제/화장지')");
        db.execSQL("INSERT INTO BEACON_LIST (SSN, MINOR, NAME) VALUES ('50114917', '14917', '헤어/세안/바디')");



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

        Cursor c = db.rawQuery("SELECT name FROM GOODS;", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Corners_forspinner() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();
        int count = 0;
        arrlist.add("전체보기");
        Cursor c = db.rawQuery("SELECT NAME FROM CORNER;", null);
        while(c.moveToNext()) {
            count++;
            if(count==24) {
                break;
            }
            arrlist.add(c.getString(0));
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Goods_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();
        Cursor c;
        if (goodsName.equals("")) {
            c = db.rawQuery("SELECT weight, price, c_id FROM GOODS;", null);
        }
        else {
            c = db.rawQuery("SELECT weight, price, c_id FROM GOODS where name = '" + goodsName + "';", null);
        }
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
        Cursor c = db.rawQuery("SELECT id FROM GOODS where name = '" + goodsName + "';", null);

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

    public ArrayList<String> select_Cart_forMap_bycornername(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT NAME FROM CART WHERE C_NAME = '" +  name + "';", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_Corners() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT NAME FROM CORNER;", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0));
        }
        db.close();
        return arrlist;
    }

    public String select_cartPrice_byname(String goodsName) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "";

        Cursor c = db.rawQuery("SELECT price FROM GOODS where name = '" + goodsName + "';", null);
        while(c.moveToNext()) {
            str = c.getString(0);
        }
        db.close();
        return str;
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

    public String select_BeaconList(String minor) {
        SQLiteDatabase db = getWritableDatabase();
        String str = "";

        Cursor c = db.rawQuery("SELECT NAME FROM BEACON_LIST where MINOR = '" + minor + "';", null);
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


    //db.execSQL( "INSERT INTO SHOPING_LIST (date, name, price, EA) VALUES ('2016년 05월 31일','블루베리', 990, '1')" );
    public void insert_shopingList(String date, String year, String month, String day, String name, String price, String EA) {
        SQLiteDatabase db = getWritableDatabase();

        sql = "INSERT INTO SHOPING_LIST (date, year, month, day, name, price, EA) VALUES ( '" + date + "', '" + year + "', '" + month + "', '" + day + "' , '" + name + "', '" + price + "', '" + EA + "' );";

        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Listview_item_tab3> select_shoping_list(String year, String month) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Listview_item_tab3> arrlist = new ArrayList<Listview_item_tab3>();

        Cursor c = db.rawQuery("SELECT DATE, NAME, EA, PRICE FROM SHOPING_LIST WHERE year = '" +  year + "' AND month = '" + month + "' order by DATE", null);
        while(c.moveToNext()) {
            Listview_item_tab3 temp = new Listview_item_tab3(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
            arrlist.add(temp);
        }
        db.close();
        return arrlist;
    }

    public ArrayList<String> select_shoping_list_all() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrlist = new ArrayList<String>();

        Cursor c = db.rawQuery("SELECT DATE, NAME FROM SHOPING_LIST;", null);
        while(c.moveToNext()) {
            arrlist.add(c.getString(0) + c.getString(1));
        }
        db.close();
        return arrlist;
    }

}