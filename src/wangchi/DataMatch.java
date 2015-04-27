package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DataMatch {

	Vector<Vector> Yjilu;// 创建存放获取数据的列表
	Vector<Vector> Djilu;// 创建存放获取数据的列表
	Vector<Vector> Jjilu;
	
	String YCode; // 获取的源序号
	String DCode;// 获取的对比用的序号
	
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	// 获取源数据
	public Vector GetYDate() {
		Yjilu=new Vector<Vector>();
		try {
			String sql = "select code,xinghao from cailiaozhouqi";
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();
			//System.out.println("获取到了结果");
			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				//System.out.print(rs.getString(1)+"\t");
			//	System.out.println(rs.getString(2));
				Yjilu.add(hang);
			}
			//System.out.println("Yjilu共有"+Yjilu.size()+"条数据");
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
		return Yjilu;
	}

	// 获取对比数据
	public Vector GetDDate() {
		Djilu = new Vector<Vector>();
		try {
			String sql = "select code,version from allcailiao";
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Vector hang2 = new Vector();
				hang2.add(rs.getString(1));
				hang2.add(rs.getString(2));
			//	System.out.print(rs.getString(1)+"\t");
				//System.out.println(rs.getString(2));
				Djilu.add(hang2);
			}
		//	System.out.println("Djilu共有"+Djilu.size()+"条数据");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("出错");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return Djilu;
	}

	// 对比两组数据,对匹配的数据进行装入
	public Vector<Vector> Match(Vector<Vector> Yjilu, Vector<Vector> Djilu) {
		Jjilu = new Vector<Vector>();
//		System.out.println("Djilu数据量为"+Djilu);
//		System.out.println("Yjilu数据量为"+Yjilu.get(1).get(0).toString());
		// 用for循环确定循环的次数
		for (int i = 0; i < Yjilu.size(); i++) {
			Vector y=Yjilu.get(i);
			YCode= (String) y.get(0);
//			System.out.println("第"+i+"个YCode为"+YCode);
			
			for (int j = 0; j < Djilu.size(); j++) {
				Vector v = Djilu.get(j);
				DCode = (String)v.get(0);
	//			System.out.println("第"+j+"个DCode为"+DCode);
				
				if (YCode.equals(DCode)) {
					System.out.println("第"+i+"个配对成功");
					Vector insert = new Vector();
					String version = (String) v.get(1);
					insert.add(YCode);
					insert.add(version);
					Jjilu.add(insert);
					continue;
				}
			}
		}
		return Jjilu;
	}

	// 将匹配好的数据插入表中
	public void intertYData(Vector<Vector>Jjilu) {
		int p=0;
		try {
			String sql = "update cailiaozhouqi set xinghao=? where code=?";
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
		
			for(int i = 0; i<Jjilu.size();i++){
				ps = (PreparedStatement) ct.prepareStatement(sql);
			    ps.setString(1, (String)Jjilu.get(i).get(1)); //传入型号信息
			    ps.setString(2, (String)Jjilu.get(i).get(0)); //传入编码
			    p = ps.executeUpdate();//更新程序
			    if (p> 0) {
					System.out.println("第"+i+"次更新成功");
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("出错");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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


}
