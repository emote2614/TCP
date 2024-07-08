package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private String[] resultArr;
	private String[] rankArr;
	
	public DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ticket";
			conn  = DriverManager.getConnection(url, "root", "201945008");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러: " + e);
		} 
	}

	
	public String[] gameResult() {
		resultArr = new String[3];
		try {
			String user, score;

			String sql = "select name, score from rankdb order by num desc limit 2";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int i = 0;
			while(rs.next()){
                user = rs.getString(1);
                score = rs.getString(2);
                
                resultArr[i] = user + "," + score;
                i++;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultArr;
	}
	
	public void resultInsert(String username, int score) {
		try {
			String sql = "insert into rankdb (name, score) values ('" + username + "'," + score + ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String[] rank() {
		try {
			String user, score;
			rankArr = new String[3];
			String sql = "select name, score from rankdb order by score desc limit 3";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int i = 0;
			while(rs.next()){
                user = rs.getString(1);
                score = rs.getString(2);
                rankArr[i] = "<html><body><center>" + score + "<p>" + user + "</center></body></html>";
                i++;
            }
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rankArr;
	}
}
