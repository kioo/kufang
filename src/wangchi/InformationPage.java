package wangchi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class InformationPage extends AbstractTableModel {

	/**
	 * 库品列表页面
	 */
	Vector<String> ziduan;
	Vector<Vector>jilu;//创建存放获取数据的列表
	DataBaseTest db;
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
		
		db = new DataBaseTest();
		jilu = new Vector<Vector>();
		jilu = db.Query(sql);
	}
}
