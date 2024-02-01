package 개발자괴롭히기;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// DAO : Data Access Object
// Member table 과 data를 주고 받는 기능들을 모아둔 클래스

public class DeveloperDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// 회원가입 기능

	public int join(DeveloperDTO dto) {

		String id = dto.getId();
		String pw = dto.getPw();

		int cnt = 0;
		// JDBC 회원등록
		// 1. 드라이버 로드 (동적로딩)
		// -컴파일러가 아래 코드를 보고 해당 드라이버를 찾아가서, 적용하기 때문

		try {
			// 에러가 나는 이유
			// 해당 위치에 드라이버 파일이 없을 수도 있고
			// 잘못된 경로를 입력했을 때
			connection();

			// 3.SQL문장 실행
			// -PreparedStatement

			String sql = "INSERT INTO member_TB VALUES(?,?)"; // 바인드 변수

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			// exexuteUpdate()는 실행한 문장의 개수를 반환
			cnt = psmt.executeUpdate(); // 테이블에 영향을 주는 것들은 update
//executeQuery = 테이블에 영향을 주지 않는 것들을 씀 (select 등)

			// MVC : Model View Controller
			// Model - DAO / DTO(VO) : filed
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// catch 를 넘어가거나 아니면 try가 잘 실행되어도
			// 무조건 마지막에 실행되는 부분
			close();

		}
		return cnt;
	}

//	로그인 기능
	public DeveloperDTO login(String ID, String PW) {
		// DB 연동 해주는 메소드

		DeveloperDTO info = null;

		try {
			connection();

			String sql = " select * from member_TB where id=? and pw=? ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ID);
			psmt.setString(2, PW);

//			executeUpdate() : insert, update, delete
//			실행에 성공했을 때 수행된 row 수를 반환 (int 타입)
//			executeQuery() : select
//			ResultSet : 커서 객체(자바와 sql 간의 새로운 통로 이름)

			rs = psmt.executeQuery();

			if (rs.next()) {
				String loginId = rs.getString(1);
				String loginPw = rs.getString("pw");

				info = new DeveloperDTO(loginId, loginPw);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return info;

	}

	public ArrayList<DeveloperDTO> memberList() {
		// 회원들의 목록을 저장할 ArrayList 생성
		ArrayList<DeveloperDTO> list = new ArrayList<DeveloperDTO>(); // 나중에 characterDTO로 변경

		try {
			connection();
			String sql = " select * from member_TB order by id";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {

				String Id = rs.getString(1);
				String Pw = rs.getString(2);


				DeveloperDTO dto = new DeveloperDTO(Id, Pw);
				// 나중에 characterDTO로 변경

				list.add(dto);

			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}

		return list;

	}

	public int delete(String id) {
		// 탈퇴 메소드
		int cnt = 0;
		connection();

		try {

			String sql = " delete from member_TB where id = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	
	
	
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}

			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 연결
			// 연결하기 위해서는 3개의 정보 필요(주소, 계정,비밀번호)
			String db_url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String db_id = "campus_23K_AI18_p1_4";
			String db_pw = "smhrd4";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);

			if (conn != null) {

			} else {
				System.out.println("Connection 연결 실패");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
