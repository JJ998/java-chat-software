package Client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener{
	protected JLabel lbUserName,lbPSW,lbConfirmedPSW;
	protected JTextField txtUserName;
	protected JPasswordField txtPSW,txtConfirmedPSW;
	protected JButton btnRegister, btnReset;
	
	public Register() {
		super("用户注册");
		initComponent();
	}
	
	//初始化控件
	public void initComponent() {
		lbUserName = new JLabel("用户名");
		lbPSW = new JLabel("密码");
		lbConfirmedPSW = new JLabel("确认密码");
		
		txtUserName = new JTextField(10);
		txtPSW = new JPasswordField(10);
		txtConfirmedPSW = new JPasswordField(10);
		
		btnReset = new JButton("重置");
		btnRegister = new JButton("注册");
		
		btnReset.addActionListener(this);
		btnRegister.addActionListener(this);
		
		this.setLayout(new GridLayout(4,2));
		this.add(lbUserName);
		this.add(txtUserName);
		this.add(lbPSW);
		this.add(txtPSW);
		this.add(lbConfirmedPSW);
		this.add(txtConfirmedPSW);
		this.add(btnRegister);
		this.add(btnReset);
		
		txtUserName.setFocusable(true);
		
		this.setSize(400,200);
		this.setVisible(true); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn == btnRegister) {
			if(txtUserName.getText().equals("") || txtUserName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "用户名不能为空！","注册失败",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtPSW.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this,"登陆密码不能为空","注册失败",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtConfirmedPSW.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this,"确认密码不能为空","注册失败",JOptionPane.ERROR_MESSAGE);
				return;
			}

			if(!String.valueOf(txtConfirmedPSW.getPassword()).equals(String.valueOf(txtPSW.getPassword()))) {
				JOptionPane.showMessageDialog(this, "两次密码必须一致", "注册失败",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String userName = null;
			String password = null;
			int i;
			
			userName = txtUserName.getText().trim();
			password = new String(txtPSW.getPassword());
			
			for(i = 0;i < Data.customers.size();i++) {
				if(Data.customers.get(i).getUserName().equals(userName)) {
					break;
				}
			}
			
			if(i < Data.customers.size()) {
				JOptionPane.showMessageDialog(this,"该用户名已经被注册，请选用其他用户名！","注册失败",JOptionPane.ERROR_MESSAGE);
			}
			else {
				Data.customers.add(new Customer(userName,password));
				JOptionPane.showMessageDialog(this,"恭喜"+userName+"注册成功,请牢记您的用户名和密码。\n单击\"  确定\"按钮开始登陆","注册成功",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				new Login();
			}
		}
		else if(btn == btnReset) {
			txtUserName.setText("");
			txtPSW.setText("");
			txtConfirmedPSW.setText("");
			txtUserName.setFocusable(true);
		}
		
	}

}