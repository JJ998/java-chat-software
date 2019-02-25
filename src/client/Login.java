package Client;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javafx.event.ActionEvent;

public class Login extends JFrame implements ActionListener{

	protected JLabel lblPrompt, lbUserName, lbPSW;
	protected JTextField txtUserName;
	protected JPasswordField txtPSW;
	protected JButton btnLogin, btnRegister;
	protected static Thread thread = null;
	
	public static void main(String[] args) {
		Data.init();
		Login login = new Login();
		
	}
	
	public Login() {
		super("用户登陆");
		initComponent();
	}
	
	//初始化控件
	public void initComponent() {
		lbUserName = new JLabel("用户名：");
		lbPSW = new JLabel("密码：");
		
		txtUserName = new JTextField(10);
		txtPSW = new JPasswordField(10);
		
		btnLogin = new JButton("登陆");
		btnRegister = new JButton("注册");
		
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
		
		this.setLayout(new GridLayout(3,2));
		this.add(lbUserName);
		this.add(txtUserName);
		this.add(lbPSW);
		this.add(txtPSW);
		this.add(btnLogin);
		this.add(btnRegister);
		
		txtUserName.setFocusable(true);
		
		this.setSize(300,200);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn == btnLogin) {
			if(txtUserName.getText().equals("") || txtUserName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this,"用户名不能为空！", "登陆失败",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtPSW.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this, "登录密码不能为空！", "登录失败", JOptionPane.ERROR_MESSAGE);
                return;
			}
			String userName = null;
			String password = null;
			
			userName = txtUserName.getText().trim();
			password = new String(txtPSW.getPassword());
			int i;
			
			for(i=0; i < Data.customers.size(); i++) {
				if(Data.customers.get(i).getUserName().equals(userName) && Data.customers.get(i).getPSW().equals(password)) {
                    break;
                }
			}
			
			if(i < Data.customers.size()) {
                JOptionPane.showMessageDialog(this, "欢迎您，" + userName, "登录成功", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                Customer cus = new Customer("我", "111");
                new HomePage(cus);
            }
            else {
                JOptionPane.showMessageDialog(this, "登录失败，请检查用户名和密码是否正确......", "登录失败", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(btn == btnRegister) {
            this.dispose();
            new Register();
        }
	}

	
}
