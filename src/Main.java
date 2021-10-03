import java.sql.*;

public class Main {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			// 현재 SQLite JDBC가 있는지 확인
			Class.forName("org.sqlite.JDBC");

			// SQLite db 파일에 연결
			String dbFileName = "myfirst.db";
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);

			// 데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			Statement state1 = connection.createStatement();
			String sql1 = "select *from g_artists";
			ResultSet rs1 = state1.executeQuery(sql1);
			while (rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			state1.close();

			// 데이터 추가
			System.out.println("\n*** 새 데이터 추가 ***");
			Statement state2 = connection.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)"
					+ " values ('방탄소년단', '남성', '2010년대', '2013년', datetime('now', 'localtime'));";
			int count2 = state2.executeUpdate(sql2);
			if (count2 > 0)
				System.out.println("새로운 데이터가 추가되었습니다.");
			else
				System.out.println("[ERROR] 데이터 추가 오류.");
			state2.close();

			// 데이터 수정
			System.out.println("\n*** 데이터 수정 ***");
			Statement state3 = connection.createStatement();
			String sql3 = "update g_artists set a_year = '2000년대, 2010년대, 2020년대' " + "where id=1 ;";
			int count3 = state3.executeUpdate(sql3);
			if (count3 > 0)
				System.out.println("데이터가 수정되었습니다.");
			else
				System.out.println("[ERROR] 데이터 수정 오류.");
			state3.close();

			// 데이터 삭제
			System.out.println("\n*** 데이터 삭제 ***");
			Statement state4 = connection.createStatement();
			String sql4 = "delete from g_artists where id=6 ;";
			int count4 = state4.executeUpdate(sql4);
			if (count4 > 0)
				System.out.println("데이터가 삭제되었습니다.");
			else
				System.out.println("[ERROR] 데이터 삭제 오류.");
			state4.close();

			// 데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			Statement state = connection.createStatement();
			String sql = "select *from g_artists";
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				System.out.println(id + " " + name);
			}
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
			}
		}
	}

}