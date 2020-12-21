package test.mariadb;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectTest {
	public static void main(String[] args) {
		List<BookVo> list = select();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		
	}
	
	public static List<BookVo> select(){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver"); // 클래스가 없으면 에러가 생기기 때문에

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.0.114:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결 성공!");
			
			
			//  3. sql 준비
			String sql = "select no, title, author, price from book order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			// 4.바인딩 //여기에는 없음 selelct

		
			//  5. sql문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				Integer price = rs.getInt(4);
				
				BookVo vo = new BookVo(); //값을 객체에 집어넣음
				
				vo.setNo(no);
				vo.setTitle(title);
				vo.setAuthor(author);
				vo.setPrice(price);
				
				list.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error 에러:" + e); // 비밀번호같은게 틀릴수 있기 때문에
		} finally {
			// 3. 자원정리 -> 커넥션은 자원을 계속 사용하기 떄문에 종료해야한다.
			try {
				if(rs != null) {
					rs.close();
				}
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
		return list;
	
	}

}
