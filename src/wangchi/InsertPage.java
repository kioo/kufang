package wangchi;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class InsertPage extends JDialog implements ActionListener{
	JLabel bq1,bq2,bq3,bq4,bq5,bq6,bq7,bq8,bq9,bq10;
	JTextField wbk1,wbk2,wbk3,wbk4,wbk5,wbk6,wbk7,wbk8,wbk9,wbk10;
	JButton an1,an2;
	JPanel mb1,mb2,mb3,mb4;
	
	public InsertPage(Frame fck,String ckm,Boolean msck){
		super(fck,ckm,msck);
		bq1 = new JLabel("                           数量      ");
		bq2 = new JLabel("                           岗位       ");
		bq3 = new JLabel("                           物资编码      ");
		bq4 = new JLabel("                           物资名称      ");
		bq5 = new JLabel("                           型号      ");
		bq6 = new JLabel("                           材料单位      ");
		bq7 = new JLabel("                           单价        ");
		bq8 = new JLabel("                           周期单位      ");
		bq9 = new JLabel("                           使用周期      ");
		bq10 = new JLabel("                           更改时间      ");
		
		wbk1 = new JTextField(5);
		wbk2 = new JTextField(5);
		wbk3 = new JTextField(5);
		wbk4 = new JTextField(5);
		wbk5 = new JTextField(5);
		wbk6 = new JTextField(5);
		wbk7 = new JTextField(5);
		wbk8 = new JTextField(5);
		wbk9 = new JTextField(5);
		wbk10 = new JTextField(5);
		
		an1 = new JButton("添加");
		an1.addActionListener(this);
		an1.setActionCommand("tianjia2");
		an2= new JButton("取消");
		an2.addActionListener(this);
		an2.setActionCommand("quxiao");
		
		mb1 = new JPanel();
		mb2 = new JPanel();
		mb3 = new JPanel();
		mb4 = new JPanel();
		
		mb1.setLayout(new GridLayout(10,1));
		mb2.setLayout(new GridLayout(10,1));
		
		mb1.add(bq1);  mb1.add(bq2);  mb1.add(bq3);
		mb1.add(bq4);  mb1.add(bq5);  mb1.add(bq6);
		mb1.add(bq7);  mb1.add(bq8);  mb1.add(bq9);
		mb1.add(bq10);
		
		mb2.add(wbk1);  mb2.add(wbk2);  mb2.add(wbk3);
		mb2.add(wbk4);  mb2.add(wbk5);  mb2.add(wbk6);
		mb2.add(wbk7);  mb2.add(wbk8);  mb2.add(wbk9);
		mb2.add(wbk10);
		
		mb3.add(an1);
		mb3.add(an2);
		//将每个部分的面板加入
		this.add(mb1,BorderLayout.WEST);
		this.add(mb2);
		this.add(mb3,BorderLayout.SOUTH);
		this.add(mb4, BorderLayout.EAST);		
		
		//设置JFrame的格式
				this.setSize(370,400);
				this.setLocation(401, 281);
				this.setResizable(false);//设置是否可以被用户调整
				this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("tianjia2")){
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			Statement sm = null;
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				ct = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/shangpin?user=root&password=5218791kioo)&useUnicode=true&characterEncoding=UTF8");
				String ss = ("insert into cailiaozhouqi values(?,?,?,?,?,?,?,?,?,?)");
				ps = (PreparedStatement) ct.prepareStatement(ss);
				ps.setInt(1, Integer.parseInt(wbk1.getText()));
				ps.setString(2, wbk2.getText());
				ps.setString(3,wbk3.getText());
				ps.setString(4, wbk4.getText());
				ps.setString(5, wbk5.getText());
				ps.setString(6, wbk6.getText());
				ps.setFloat(7, Float.parseFloat(wbk7.getText()));
				ps.setString(8, wbk8.getText());
				ps.setString(9, wbk9.getText());
				ps.setDate(10,new java.sql.Date(new Date().getTime()));
				ps.executeUpdate();
				
				this.dispose();//关闭这个窗口
			}catch(Exception e2){
				
			}finally{
				try{
					if(rs!=null){
						rs.close();
					}
					if(ps!=null){
						ps.close();
					}
					if(ct!=null){
						ct.close();
					}
				}catch(Exception e3){}
			}
		}
		else if(e.getActionCommand().equals("quxiao")){
			this.dispose();
		}
	}
}
