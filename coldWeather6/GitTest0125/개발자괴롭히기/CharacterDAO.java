package 개발자괴롭히기;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CharacterDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;

	// 이름, 스탯 입력 getName, getStats

	public int inputStats(CharacterDTO cdto) {
		String name = cdto.getName();
		int hp = cdto.getHp();
		int intell = cdto.getIntell();
		int fp = cdto.getFp();
		int mp = cdto.getMp();
		int ment = cdto.getMent();
		int salary = cdto.getSalary();
		int cnt = 0;

		connection();
		try {
			String sql = "INSERT INTO character_TB VALUES (?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setInt(2, hp);
			psmt.setInt(3, intell);
			psmt.setInt(4, fp);
			psmt.setInt(5, mp);
			psmt.setInt(6, ment);
			psmt.setInt(7, salary);
			cnt = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

//	캐릭터 랭킹 기능
	public ArrayList<String> CharacterList() {

		ArrayList<String> list = new ArrayList<String>();
		CharacterDTO cdto = null;
		try {
			connection();
			String sql = "SELECT NAME \r\n" + "  FROM character_TB \r\n" + "  ORDER BY SALARY DESC";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String Name = rs.getString(1);

//				cdto = new CharacterDTO(Name);

				list.add(Name);

			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}

		return list;

	}

	public ArrayList<Integer> salaryList() {

		ArrayList<Integer> salaryList = new ArrayList<Integer>();
		CharacterDTO cdto = null;
		try {
			connection();
			String sql = "SELECT SALARY \r\n" + "  FROM character_TB \r\n" + "  ORDER BY SALARY DESC";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Integer salary = rs.getInt(1);

//				cdto = new CharacterDTO(Name);

				salaryList.add(salary);

			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}

		return salaryList;
	}

	// 캐릭터 삭제기능
	public int cDelete(String name) {

		int cnt = 0;

		try {
			connection();

			String sql = "DELETE FROM CHARACTER_TB WHERE NAME = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			cnt = psmt.executeUpdate();
			conn.commit(); // 추가: 변경사항 커밋
		} catch (Exception e) {
			e.printStackTrace(); // 예외 상세 정보 출력
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private void connection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String db_url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";
			String db_id = "campus_23K_AI18_p1_4";
			String db_pw = "smhrd4";
			conn = DriverManager.getConnection(db_url, db_id, db_pw);

			if (conn != null) {

			} else {
				System.out.println("Connection 연결 실패");
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
