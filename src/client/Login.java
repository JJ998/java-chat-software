package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Login extends JFrame implements ActionListener {

    protected static Thread thread = null;
    protected JLabel lblPrompt, lbUserName, lbPSW;
    protected JTextField txtUserName;
    protected JPasswordField txtPSW;
    protected JButton btnLogin, btnRegister;

    public Login() {
        super("Login");
        initComponent();
    }

    public static void main(String[] args) {
        Data.init();
        Login login = new Login();
    }

    public void initComponent() {
        lbUserName = new JLabel("username");
        lbPSW = new JLabel("password");

        txtUserName = new JTextField(10);
        txtPSW = new JPasswordField(10);

        btnLogin = new JButton("Sign in");
        btnRegister = new JButton("Sign up");

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        this.setLayout(new GridLayout(3, 2));
        this.add(lbUserName);
        this.add(txtUserName);
        this.add(lbPSW);
        this.add(txtPSW);
        this.add(btnLogin);
        this.add(btnRegister);

        txtUserName.setFocusable(true);

        this.setSize(300, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        if (btn == btnLogin) {
            if (txtUserName.getText().equals("") || txtUserName.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "please input username", "empty username",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtPSW.getPassword().equals("")) {
                JOptionPane.showMessageDialog(this, "please input password", "empty password", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String userName = null;
            String password = null;

            userName = txtUserName.getText().trim();
            password = new String(txtPSW.getPassword());
            int i;

            for (i = 0; i < Data.customers.size(); i++) {
//                if (Customer.getUserName().equals(userName) && Data.customers.get(i).getPSW().equals(password)) {
//                    break;
//                }
            }

            Customer user = Data.getCustomerbyUsername(userName);

            if (i < Data.customers.size()) {
                JOptionPane.showMessageDialog(this, "welcome, " + userName, "welcome", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                Customer cus = new Customer("test", "111");
                new HomePage(cus);
            } else {
                JOptionPane.showMessageDialog(this, "wrong username or password", "login failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (btn == btnRegister) {
            this.dispose();
            new Register();
        }
    }


}
