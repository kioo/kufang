package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//���ݿ�ҳ��,change
public class DataBaseTest {
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	Vector<Vector> jilu;// ������Ż�ȡ���ݵ��б�

	public DataBaseTest() {
		// ��ʼ���������ݿ�������������ݿ�
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 1.��ѯ������Ϣ
	public Vector<Vector> Query() {
		jilu = new Vector<Vector>(); // ������ѯ�����ŵ�����
		try {
			String sql = "select * from cailiaozhouqi";
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getInt(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getFloat(7));
				hang.add(rs.getString(8));
				hang.add(rs.getString(9));
				hang.add(rs.getDate(10));
				jilu.add(hang);
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
		return jilu;
	}

	// 2.ͨ�����Ʋ�ѯ

	// 3.ͨ���ֶβ�ѯ

	// 4.���

	// 5.�޸�

	// 6.ɾ��
	public static void main(String[] args) {
		DataBaseTest d = new DataBaseTest();
		d.Query();
	}
}
