package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
//Êý¾Ý¿âÒ³Ãæ
public class DataBaseTest {
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	public void Query() {
		try {
			String sql = "select code2 from gangwei_cailiao";
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int i=0;
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(i++);
			}
		} catch (Exception e) {
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		DataBaseTest d = new DataBaseTest();
		d.Query();
	}
}
