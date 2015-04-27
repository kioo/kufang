package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DataMatch {

	Vector<Vector> Yjilu;// ������Ż�ȡ���ݵ��б�
	Vector<Vector> Djilu;// ������Ż�ȡ���ݵ��б�
	Vector<Vector> Jjilu;
	
	String YCode; // ��ȡ��Դ���
	String DCode;// ��ȡ�ĶԱ��õ����
	
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;

	// ��ȡԴ����
	public Vector GetYDate() {
		Yjilu=new Vector<Vector>();
		try {
			String sql = "select code,xinghao from cailiaozhouqi";
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();
			//System.out.println("��ȡ���˽��");
			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				//System.out.print(rs.getString(1)+"\t");
			//	System.out.println(rs.getString(2));
				Yjilu.add(hang);
			}
			//System.out.println("Yjilu����"+Yjilu.size()+"������");
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

	// ��ȡ�Ա�����
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
		//	System.out.println("Djilu����"+Djilu.size()+"������");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����");
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

	// �Ա���������,��ƥ������ݽ���װ��
	public Vector<Vector> Match(Vector<Vector> Yjilu, Vector<Vector> Djilu) {
		Jjilu = new Vector<Vector>();
//		System.out.println("Djilu������Ϊ"+Djilu);
//		System.out.println("Yjilu������Ϊ"+Yjilu.get(1).get(0).toString());
		// ��forѭ��ȷ��ѭ���Ĵ���
		for (int i = 0; i < Yjilu.size(); i++) {
			Vector y=Yjilu.get(i);
			YCode= (String) y.get(0);
//			System.out.println("��"+i+"��YCodeΪ"+YCode);
			
			for (int j = 0; j < Djilu.size(); j++) {
				Vector v = Djilu.get(j);
				DCode = (String)v.get(0);
	//			System.out.println("��"+j+"��DCodeΪ"+DCode);
				
				if (YCode.equals(DCode)) {
					System.out.println("��"+i+"����Գɹ�");
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

	// ��ƥ��õ����ݲ������
	public void intertYData(Vector<Vector>Jjilu) {
		int p=0;
		try {
			String sql = "update cailiaozhouqi set xinghao=? where code=?";
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
		
			for(int i = 0; i<Jjilu.size();i++){
				ps = (PreparedStatement) ct.prepareStatement(sql);
			    ps.setString(1, (String)Jjilu.get(i).get(1)); //�����ͺ���Ϣ
			    ps.setString(2, (String)Jjilu.get(i).get(0)); //�������
			    p = ps.executeUpdate();//���³���
			    if (p> 0) {
					System.out.println("��"+i+"�θ��³ɹ�");
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����");
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
