package wangchi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class InformationPage extends AbstractTableModel {

	/**
	 * 库品列表页面
	 */
	Vector<String> ziduan;
	Vector<Vector>jilu;//创建存放获取数据的列表
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	@Override
	public int getColumnCount() {
		// 获取列的总数
		return this.ziduan.size();
	}

	@Override
	public int getRowCount() {
		// 获取行的总数
		return this.jilu.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// 输入行和列的数获取值
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
		ziduan.add("数量");
		ziduan.add("岗位");
		ziduan.add(" 物资编码");
		ziduan.add(" 物资名称");
		ziduan.add("型号");
		ziduan.add("材料单位");
		ziduan.add("单价");
		ziduan.add("周期单位");
		ziduan.add("使用周期");
		ziduan.add("改变日期");
		
		jilu = new Vector<Vector>();
		
		//连接数据库
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
					System.out.println("查询到的行数为:"+jilu.size());
				}
			} catch (Exception e){}
		}
	}
}
