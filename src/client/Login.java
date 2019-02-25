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
		super("�û���½");
		initComponent();
	}
	
	//��ʼ���ؼ�
	public void initComponent() {
		lbUserName = new JLabel("�û�����");
		lbPSW = new JLabel("���룺");
		
		txtUserName = new JTextField(10);
		txtPSW = new JPasswordField(10);
		
		btnLogin = new JButton("��½");
		btnRegister = new JButton("ע��");
		
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
				JOptionPane.showMessageDialog(this,"�û�������Ϊ�գ�", "��½ʧ��",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtPSW.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this, "��¼���벻��Ϊ�գ�", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "��ӭ����" + userName, "��¼�ɹ�", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                Customer cus = new Customer("��", "111");
                new HomePage(cus);
            }
            else {
                JOptionPane.showMessageDialog(this, "��¼ʧ�ܣ������û����������Ƿ���ȷ......", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(btn == btnRegister) {
            this.dispose();
            new Register();
        }
	}

	
}
