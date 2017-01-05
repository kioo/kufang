package wangchi;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UpdataPage extends JDialog implements ActionListener {
	DataBaseTest db;
	JLabel bq1,bq2,bq3,bq4,bq5,bq6,bq7,bq8,bq9,bq10;
	JTextField wbk1,wbk2,wbk3,wbk4,wbk5,wbk6,wbk7,wbk8,wbk9,wbk10;
	JButton an1, an2;
	JPanel mb1, mb2, mb3, mb4;
	
	public UpdataPage(Frame fck,String ckm,Boolean msck,InformationPage infpage,int hang) {//TODO ��������Դ��ѡ���id
		super(fck, ckm, msck);
		bq1 = new JLabel("                            ����          ");
		bq2 = new JLabel("                            ��λ      ");
		bq3 = new JLabel("                            ���ʱ���          ");
		bq4 = new JLabel("                            ��������   ");
		bq5 = new JLabel("                            �ͺ�  ");
		bq6 = new JLabel("                            ���ϵ�λ          ");
		bq7 = new JLabel("                            ����  ");
		bq8 = new JLabel("                            ���ڵ�λ          ");
		bq9 = new JLabel("                            ʹ������  ");
		bq10 = new JLabel("                            ��������  ");
		
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
		wbk10.setText("����ʱ��Ϊ��ǰʱ��");
		wbk10.setEditable(false);
		
		an1 = new JButton("�޸�");
		an1.addActionListener(this);
		an1.setActionCommand("xiugai");// ���ü����ؼ���

		an2 = new JButton("ȡ��");
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
			db = new DataBaseTest();
			db.updateData(getData());
			this.dispose();
		} else if (e.getActionCommand().equals("quxiao")) {
			this.dispose();
		}
	}
	
	public Vector getData(){
		
		Vector data = new Vector();
		// ע���ȡԭʼ������object����,��Ҫǿ��ת����String����
		data.add((String) wbk1.getText());
		data.add((String) wbk2.getText());
		data.add((String) wbk3.getText());
		data.add((String) wbk4.getText());
		data.add((String) wbk5.getText());
		data.add((String) wbk6.getText());
		data.add((String) wbk7.getText());
		data.add((String) wbk8.getText());
		data.add((String) wbk9.getText());
		return data;
	}

	public void test(){
		int a=10;
	}
}
