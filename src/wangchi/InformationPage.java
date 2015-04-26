package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class InformationPage extends AbstractTableModel {

	/**
	 * ��Ʒ�б�ҳ��
	 */
	Vector<String> ziduan;
	Vector<Vector>jilu;//������Ż�ȡ���ݵ��б�
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	@Override
	public int getColumnCount() {
		// ��ȡ�е�����
		return this.ziduan.size();
	}

	@Override
	public int getRowCount() {
		// ��ȡ�е�����
		return this.jilu.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// �����к��е�����ȡֵ
		return ((Vector)this.jilu.get(row)).get(column);
	}
	@Override
	public String getColumnName(int column) {
		return (String) ziduan.get(column);
	}
	
	public InformationPage() {
		String sql = "select * from cailiaozhouqi";
		this.sqlyj(sql);
	}
	public InformationPage(String sql) {
		this.sqlyj(sql);
	}
	
	public void sqlyj(String sql){
		ziduan = new Vector<String>();
		ziduan.add("����");
		ziduan.add("��λ");
		ziduan.add(" ���ʱ���");
		ziduan.add(" ��������");
		ziduan.add("�ͺ�");
		ziduan.add("���ϵ�λ");
		ziduan.add("����");
		ziduan.add("���ڵ�λ");
		ziduan.add("ʹ������");
		ziduan.add("�ı�����");
		
		jilu = new Vector<Vector>();
		
		//�������ݿ�
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ct = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
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
		}catch(Exception e){}
		finally{
			try {
	    		if(rs!=null)
				{
					rs.close();
				}
	    		if(ps!=null)
				{
					ps.close();
				}
				if(ct!=null)
				{
					ct.close();
					System.out.println("��ѯ��������Ϊ:"+jilu.size());
				}
			} catch (Exception e){}
		}
	}
}
