import java.sql.*;

public class DBDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
	String user = "jungmin";
	String pass = "1234";
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;
	String query = null;
	int result;
	Boolean string = false;

	public DBDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 검색 성공!");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.err.println("sql error = " + e.getMessage());
			System.exit(1);
		}
	}

	// select문
	public void selecting(DBDTO dto){
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			query = "select * from test";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString("name");
				int heavy = rs.getInt(3);
				int price = rs.getInt(4);
				int location = rs.getInt(5);
				int c_id = rs.getInt(6);
				if (c_id == dto.getcorner()) {
					System.out.println(" ID= " + id + ", NAME= " + name + ", HEAVY= " + heavy + ", PRICE= " + price
							+ " LOCATION= " + location + ", C_ID= " + c_id);
				}
			}
			rs.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
	}
	// insert문
	public void inserting(DBDTO dto){
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			sql = "insert into test values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getid());
			pstmt.setString(2, dto.getname());
			pstmt.setInt(3, dto.getheavy());
			pstmt.setInt(4, dto.getprice());
			pstmt.setInt(5, dto.getlocation());
			pstmt.setInt(6, dto.getc_id());
			result = pstmt.executeUpdate();
			System.out.println(result + " row inserted");
			pstmt.close();
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
	}
	// delete문
	public void deleting(DBDTO dto)
	{
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			sql = "delete from test where name =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getdename());
			result = pstmt.executeUpdate();
			System.out.println(result + " row deleted");
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
	}
	// update문
	public void updating(DBDTO dto){
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();

			if (dto.getkind().equals("price")) {
				sql = "update test set price= ? where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getnumber());
				pstmt.setString(2, dto.getupname());
			} else if (dto.getkind().equals("heavy")) {
				sql = "update test set heavy= ? where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getnumber());
				pstmt.setString(2, dto.getupname());
			} else if (dto.getkind().equals("location")) {
				sql = "update test set location= ? where name = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getnumber());
				pstmt.setString(2, dto.getupname());
			}
			result = pstmt.executeUpdate();
			System.out.println(result + " row updated");
			conn.commit();
			conn.setAutoCommit(true);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("sql error = " + e.getMessage());
		}
	}
	
	
}