package wangchi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MainPage extends JFrame implements ActionListener {

	JPanel mb1, mb2;
	JLabel bq1, bq2;
	JTextField wbk1, wbk2;
	JButton an1, an2, an3, an4,an5;
	JTable bg1;
	JScrollPane gd1;
	InformationPage infPage;

	public static void main(String[] args) {
		MainPage mp = new MainPage();
	}

	public MainPage() {
		mb1 = new JPanel();
		bq1 = new JLabel("物资编号");
		wbk1 = new JTextField(10);
		bq2 = new JLabel("       物资名称");
		wbk2 = new JTextField(10);
		
		an5 = new JButton("查询编号");
		an5.addActionListener(this);
		an5.setActionCommand("chaxunbh");
		an1 = new JButton("查询名称");
		an1.addActionListener(this);
		an1.setActionCommand("chaxunmc");
		mb1.add(bq1);
		mb1.add(wbk1);
		mb1.add(an5);
		mb1.add(bq2);
		mb1.add(wbk2);
		mb1.add(an1);

		mb2 = new JPanel();
		an2 = new JButton("添加");
		an2.setBackground(Color.BLUE);
		an2.addActionListener(this);
		an2.setActionCommand("tianjia");
		an3 = new JButton("修改");
		an3.setBackground(Color.GREEN);
		an3.addActionListener(this);
		an3.setActionCommand("xiugai");
		an4 = new JButton("删除");
		an4.setBackground(Color.RED);
		an4.addActionListener(this);
		an4.setActionCommand("shanchu");
		mb2.add(an2);
		mb2.add(an3);
		mb2.add(an4);

		infPage = new InformationPage();
		bg1 = new JTable(infPage);
		//设置内容居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		bg1.setDefaultRenderer(Object.class, r);
		bg1.setColumnModel(setColumnWidth(bg1));
		gd1 = new JScrollPane(bg1);

		this.add(gd1);
		this.add(mb1, "North");
		this.add(mb2, "South");

		this.setTitle("库房型号查询");
		this.setSize(900, 600);
		this.setLocation(201, 181);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("chaxunbh")) {

			String bianhao = this.wbk1.getText().trim();
			String sql = "select * from cailiaozhouqi where code  like '%"
					+ bianhao + "%'";
			infPage = new InformationPage(sql);
			bg1.setModel(infPage);
		}	else if (e.getActionCommand().equals("chaxunmc")) {

			String mingcheng = this.wbk2.getText().trim();
			String sql = "select * from cailiaozhouqi where name  like '%%"
					+ mingcheng + "%%'";
			infPage = new InformationPage(sql);
			bg1.setModel(infPage);
		} else if (e.getActionCommand().equals("tianjia")) {
			InsertPage tj = new InsertPage(this, "添加配件", true);
			infPage = new InformationPage();
			bg1.setModel(infPage);

		} else if (e.getActionCommand().equals("xiugai")) {
			int ii = this.bg1.getSelectedRow();
			if (ii == -1) {
				JOptionPane.showMessageDialog(this, "请选中要修改的行");
				return;
			}
			new UpdataPage(this, "修改配件信息", true, infPage, ii);

			infPage = new InformationPage();
			bg1.setModel(infPage);
		} else if (e.getActionCommand().equals("shanchu")) {
			int ii = this.bg1.getSelectedRow();
			if (ii == -1) {
				JOptionPane.showMessageDialog(this, "请选中要删除的行");
				return;
			}
			String st = (String) infPage.getValueAt(ii, 2);
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			Statement sm = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				ct = (Connection) DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
				ps = (PreparedStatement) ct
						.prepareStatement("delete from cailiaozhouqi where code=?");
				ps.setString(1, st);
				ps.executeUpdate();
			} catch (Exception e2) {
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

				} catch (Exception e3) {
				}
			}
			infPage = new InformationPage();
			bg1.setModel(infPage);
		}

	}

	// 设置表格列的宽度
	public TableColumnModel setColumnWidth(JTable table) {
		int[] width = { 20, 70, 50,95,120,40,30,38,35,45};
		TableColumnModel columns = table.getColumnModel();// 获取表格列模型
		//TableColumn column = new TableColumn();
		for (int i = 0; i < 10; i++) { // 找出列宽
			TableColumn column = columns.getColumn(i); // 获取第一个列
			column.setPreferredWidth(width[i]); // 设置列宽
			//columns.addColumn(column);
		}
		return columns;
	}
}
