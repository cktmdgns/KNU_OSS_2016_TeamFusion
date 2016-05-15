import java.sql.*;
import java.util.Scanner;

public class DBoracle {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:oraknu";
		String user = "jungmin";
		String pass = "1234";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String query = null;
		int result;

		Scanner scan = new Scanner(System.in);
		// System.out.print("몇번 코너?");
		// int cornerselect = scan.nextInt();
		// System.out.print("ID?");
		// int idinsert = scan.nextInt();
		// scan.nextLine();
		// System.out.print("name?");
		// String nameinsert = scan.nextLine();
		// System.out.print("heavy?");
		// int heavyinsert = scan.nextInt();
		// System.out.print("price?");
		// int priceinsert = scan.nextInt();
		// System.out.print("location?");
		// int locationinsert = scan.nextInt();
		// System.out.print("c_id?");
		// int c_idinsert = scan.nextInt();

		// scan.nextLine();
		// System.out.print("what delete name?");
		// String deletename = scan.nextLine();

		System.out.print("what kind update?");
		String updatekind = scan.nextLine();
		System.out.print("where update name?");
		String updatename = scan.nextLine();
		System.out.print("what update number?");
		int numberupdate = scan.nextInt();

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

		// select문

		// try {
		// conn.setAutoCommit(false);
		// Statement stmt = conn.createStatement();
		// query = "select * from test";
		// ResultSet rs = stmt.executeQuery(query);
		// while (rs.next()) {
		// int id = rs.getInt(1);
		// String name = rs.getString("name");
		// int heavy = rs.getInt(3);
		// int price = rs.getInt(4);
		// int location = rs.getInt(5);
		// int c_id = rs.getInt(6);
		// if (c_id == cornerselect) {
		// System.out.println(" ID= " + id + ", NAME= " + name + ", HEAVY= " +
		// heavy + ", PRICE= " + price
		// + " LOCATION= " + location + ", C_ID= " + c_id);
		// }
		// }
		// rs.close();
		// conn.commit();
		// conn.setAutoCommit(true);
		// stmt.close();
		// conn.close();
		// } catch (Exception e) {
		// System.err.println("sql error = " + e.getMessage());
		// }

		// insert문
		//
		// try {
		// conn.setAutoCommit(false);
		// Statement stmt = conn.createStatement();
		//
		// sql = "insert into test values(?,?,?,?,?,?)";
		// pstmt=conn.prepareStatement(sql);
		// pstmt.setInt(1,idinsert);
		// pstmt.setString(2,nameinsert);
		// pstmt.setInt(3,heavyinsert);
		// pstmt.setInt(4,priceinsert);
		// pstmt.setInt(5,locationinsert);
		// pstmt.setInt(6,c_idinsert);
		// result = pstmt.executeUpdate();
		// System.out.println(result + " row inserted");
		// pstmt.close();
		// conn.commit();
		// conn.setAutoCommit(true);
		// stmt.close();
		// conn.close();
		// } catch(Exception e) {
		// System.err.println("sql error = " + e.getMessage());
		// }

		// delete문
		//
		// try {
		// conn.setAutoCommit(false);
		// Statement stmt = conn.createStatement();
		// sql = "delete from test where name =?";
		// pstmt=conn.prepareStatement(sql);
		// pstmt.setString(1,deletename);
		// result = pstmt.executeUpdate();
		// System.out.println(result + " row deleted");
		// conn.commit();
		// conn.setAutoCommit(true);
		// stmt.close();
		// conn.close();
		// } catch (Exception e) {
		// System.err.println("sql error = " + e.getMessage());
		// }

		// update문

		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			sql = "update test set ? = ? where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatekind);
			pstmt.setInt(2, numberupdate);
			pstmt.setString(3, updatename);
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
