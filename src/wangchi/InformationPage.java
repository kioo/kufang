package wangchi;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class InformationPage extends AbstractTableModel {

	/**
	 * ��Ʒ�б�ҳ��
	 */
	Vector<String> ziduan;
	Vector<Vector>jilu;//������Ż�ȡ���ݵ��б�
	DataBaseTest db;
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
		
		db = new DataBaseTest();
		jilu = new Vector<Vector>();
		jilu = db.Query(sql);
	}
}
