package wangchi;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UpdataPage extends JDialog implements ActionListener {
	JLabel bq1,bq2,bq3,bq4,bq5,bq6,bq7,bq8,bq9,bq10;
	JTextField wbk1,wbk2,wbk3,wbk4,wbk5,wbk6,wbk7,wbk8,wbk9,wbk10;
	JButton an1, an2;
	JPanel mb1, mb2, mb3, mb4;
	
	public UpdataPage(Frame fck,String ckm,Boolean msck,InformationPage infpage,int hang) {//TODO 传入数据源和选择的id
		super(fck, ckm, msck);
		bq1 = new JLabel("                            数量          ");
		bq2 = new JLabel("                            岗位      ");
		bq3 = new JLabel("                            物资编码          ");
		bq4 = new JLabel("                            物资名称   ");
		bq5 = new JLabel("                            型号  ");
		bq6 = new JLabel("                            材料单位          ");
		bq7 = new JLabel("                            单价  ");
		bq8 = new JLabel("                            周期单位          ");
		bq9 = new JLabel("                            使用周期  ");
		bq10 = new JLabel("                            更改日期  ");
		
		wbk1 = new JTextField(5);
		wbk1.setText((String) infpage.getValueAt(hang, 0).toString());
		wbk2 = new JTextField(5);
		wbk2.setText((String) infpage.getValueAt(hang, 1));
		wbk3 = new JTextField(5);
		wbk3.setText((String)infpage.getValueAt(hang, 2));
		wbk3.setEditable(false);
		wbk4 = new JTextField(5);
		wbk4.setText((String) infpage.getValueAt(hang, 3));
		wbk5 = new JTextField(5);
		wbk5.setText((String) infpage.getValueAt(hang, 4));
		wbk6 = new JTextField(5);
		wbk6.setText((String) infpage.getValueAt(hang, 5));
		wbk7 = new JTextField(5);
		wbk7.setText((String) infpage.getValueAt(hang, 6).toString());
		wbk8 = new JTextField(5);
		wbk8.setText((String) infpage.getValueAt(hang, 7));
		wbk9 = new JTextField(5);
		wbk9.setText((String) infpage.getValueAt(hang, 8));
		wbk10 = new JTextField(5);
		wbk10.setText("更改时间为当前时间");
		wbk10.setEditable(false);
		
		an1 = new JButton("修改");
		an1.addActionListener(this);
		an1.setActionCommand("xiugai");// 设置监听关键字

		an2 = new JButton("取消");
		an2.addActionListener(this);
		an2.setActionCommand("quxiao");

		mb1 = new JPanel();
		mb2 = new JPanel();
		mb3 = new JPanel();
		mb4 = new JPanel();

		mb1.setLayout(new GridLayout(10, 1));
		mb2.setLayout(new GridLayout(10, 1));
		
		mb1.add(bq1);
		mb1.add(bq2);
		mb1.add(bq3);
		mb1.add(bq4);
		mb1.add(bq5);
		mb1.add(bq6);
		mb1.add(bq7);
		mb1.add(bq8);
		mb1.add(bq9);
		mb1.add(bq10);

		mb2.add(wbk1);
		mb2.add(wbk2);
		mb2.add(wbk3);
		mb2.add(wbk4);
		mb2.add(wbk5);
		mb2.add(wbk6);
		mb2.add(wbk7);
		mb2.add(wbk8);
		mb2.add(wbk9);
		mb2.add(wbk10);

		mb3.add(an1);
		mb3.add(an2);

		this.add(mb1, BorderLayout.WEST);
		this.add(mb2);
		this.add(mb3, BorderLayout.SOUTH);
		this.add(mb4, BorderLayout.EAST);

		this.setSize(370, 350);
		this.setLocation(401, 281);
		this.setResizable(false);
		this.setVisible(true);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("xiugai")) {
			
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			Statement sm = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				ct = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
				String ss = ("update cailiaozhouqi set amount=?,gangwei=?,name=?,xinghao=?,cailiaodanwei=?,price=?,cycle=?,usecycle=?,changetime=?  where code=?");
				ps = (PreparedStatement) ct.prepareStatement(ss);
				ps.setInt(1, Integer.parseInt(wbk1.getText())); //1 amount
				ps.setString(2, wbk2.getText());//2 gangwei
				ps.setString(3, wbk4.getText());//3 name
				ps.setString(4, wbk5.getText());//4 xinghao
				ps.setString(5, wbk6.getText());//5 cailiaodanwei
				ps.setFloat(6, Float.parseFloat(wbk7.getText())); //6 price
				ps.setString(7, wbk8.getText());//7 cycle
				ps.setString(8, wbk9.getText());//8 usecycle
				ps.setDate(9, new java.sql.Date(new Date().getTime()));//9 changtime
				ps.setString(10, wbk3.getText());//code
				ps.executeUpdate();
			} catch (Exception e2) {
					System.out.println("更新数据错误");
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
					this.dispose();
				} catch (Exception e3) {
					System.out.println("修改失败");
				}
			}
		} else if (e.getActionCommand().equals("quxiao")) {
			this.dispose();
		}
	}


}
