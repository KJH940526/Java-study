package test.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		
		for (int i = 11; i <= 20; i++) {
			BookVo vo = new BookVo();
			vo.setTitle("책1" + i);
			vo.setAuthor("저자1" + i);
			vo.setPrice(2000 * i);

			boolean result = insert(vo);
			if (result) {
				System.out.println("성공!");
			}
		}
	}
	
	public static boolean insert(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver"); // 클래스가 없으면 에러가 생기기 때문에

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.0.114:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결 성공!");
			
			
			//  3. sql 준비
			String sql =
					" insert" + 
				    " into book" +
					" values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setInt(3, vo.getPrice());
			
			//  4. sql문 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error 에러:" + e); // 비밀번호같은게 틀릴수 있기 때문에
		} finally {
			// 3. 자원정리 -> 커넥션은 자원을 계속 사용하기 떄문에 종료해야한다.
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
