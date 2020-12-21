package test.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver"); // 클래스가 없으면 에러가 생기기 때문에

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.0.114:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결 성공!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e); // 비밀번호같은게 틀릴수 있기 때문에
		} finally {
			// 3. 자원정리 -> 커넥션은 자원을 계속 사용하기 떄문에 종료해야한다.
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
