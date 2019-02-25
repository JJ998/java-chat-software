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
		super("�û�ע��");
		initComponent();
	}
	
	//��ʼ���ؼ�
	public void initComponent() {
		lbUserName = new JLabel("�û���");
		lbPSW = new JLabel("����");
		lbConfirmedPSW = new JLabel("ȷ������");
		
		txtUserName = new JTextField(10);
		txtPSW = new JPasswordField(10);
		txtConfirmedPSW = new JPasswordField(10);
		
		btnReset = new JButton("����");
		btnRegister = new JButton("ע��");
		
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
				JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�","ע��ʧ��",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtPSW.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this,"��½���벻��Ϊ��","ע��ʧ��",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(txtConfirmedPSW.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this,"ȷ�����벻��Ϊ��","ע��ʧ��",JOptionPane.ERROR_MESSAGE);
				return;
			}

			if(!String.valueOf(txtConfirmedPSW.getPassword()).equals(String.valueOf(txtPSW.getPassword()))) {
				JOptionPane.showMessageDialog(this, "�����������һ��", "ע��ʧ��",JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this,"���û����Ѿ���ע�ᣬ��ѡ�������û�����","ע��ʧ��",JOptionPane.ERROR_MESSAGE);
			}
			else {
				Data.customers.add(new Customer(userName,password));
				JOptionPane.showMessageDialog(this,"��ϲ"+userName+"ע��ɹ�,���μ������û��������롣\n����\"  ȷ��\"��ť��ʼ��½","ע��ɹ�",
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