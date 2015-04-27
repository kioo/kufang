package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//数据库页面,change
public class DataBaseTest {
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	Vector<Vector> jilu;// 创建存放获取数据的列表

	public DataBaseTest() {
		// 初始化连接数据库的驱动连接数据库
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
			/*ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=123456&useUnicode=true&characterEncoding=UTF8");*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 1.查询语句
	public Vector<Vector> Query(String sql) {
		jilu = new Vector<Vector>(); // 创建查询结果存放的容器
		try {
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

	// 4.添加
	public void insertData(Vector data) {

		String insert = ("insert into cailiaozhouqi values(?,?,?,?,?,?,?,?,?,?)");

		try {
			ps = (PreparedStatement) ct.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt((String) data.get(0)));
			ps.setString(2, (String) data.get(1));
			ps.setString(3, (String) data.get(2));
			ps.setString(4, (String) data.get(3));
			ps.setString(5, (String) data.get(4));
			ps.setString(6, (String) data.get(5));
			ps.setFloat(7, Float.parseFloat((String) data.get(6)));
			ps.setString(8, (String) data.get(7));
			ps.setString(9, (String) data.get(8));
			ps.setDate(10, new java.sql.Date(new Date().getTime()));
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("插入失败");
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

	// 5.修改
	public void updateData(Vector data) {
		String ss = ("update cailiaozhouqi set "
				                + "amount=?,gangwei=?,name=?,xinghao=?,"
                                +"cailiaodanwei=?,price=?,cycle=?,usecycle=?,changetime=?  "
                                + "where code=?");
		try {
			ps = (PreparedStatement) ct.prepareStatement(ss);
			ps.setInt(1, Integer.parseInt((String) data.get(0))); // 1 amount
			ps.setString(2, (String) data.get(1));// 2 gangwei
			ps.setString(3, (String) data.get(3));// 3 name
			ps.setString(4, (String) data.get(4));// 4 xinghao
			ps.setString(5, (String) data.get(5));// 5 cailiaodanwei
			ps.setFloat(6, Float.parseFloat((String) data.get(6))); // 6 price
			ps.setString(7, (String) data.get(7));// 7 cycle
			ps.setString(8, (String) data.get(8));// 8 usecycle
			ps.setDate(9, new java.sql.Date(new Date().getTime()));
			ps.setString(10, (String) data.get(2));// code
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("修改失败");
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
	// 6.删除
		public void delete(String code) {
			String deleteSql = ("delete from cailiaozhouqi where code=?");
			try {
				ps = (PreparedStatement) ct.prepareStatement(deleteSql);
				ps.setString(1, code);
				ps.executeUpdate();

			} catch (Exception e) {
				System.out.println("修改失败");
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
		// 获取总数据量
		public int getDataAmount(){
			
			int amount = 0;
			
			String deleteSql = ("select count(*) from cailiaozhouqi");
			try {
				ps = (PreparedStatement) ct.prepareStatement(deleteSql);
				rs =ps.executeQuery();
				while(rs.next()){
					amount = rs.getInt(1);
				}

			} catch (Exception e) {
				System.out.println("查询总数失败");
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
			return amount;
		}
}
