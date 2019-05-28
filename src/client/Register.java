package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame implements ActionListener {
    protected JLabel lbUserName, lbPSW, lbConfirmedPSW;
    protected JTextField txtUserName;
    protected JPasswordField txtPSW, txtConfirmedPSW;
    protected JButton btnRegister, btnReset;

    public Register() {
        super("Register");
        initComponent();
    }

    public void initComponent() {
        lbUserName = new JLabel("username");
        lbPSW = new JLabel("password");
        lbConfirmedPSW = new JLabel("confirm password");

        txtUserName = new JTextField(10);
        txtPSW = new JPasswordField(10);
        txtConfirmedPSW = new JPasswordField(10);

        btnReset = new JButton("reset");
        btnRegister = new JButton("sign up");

        btnReset.addActionListener(this);
        btnRegister.addActionListener(this);

        this.setLayout(new GridLayout(4, 2));
        this.add(lbUserName);
        this.add(txtUserName);
        this.add(lbPSW);
        this.add(txtPSW);
        this.add(lbConfirmedPSW);
        this.add(txtConfirmedPSW);
        this.add(btnRegister);
        this.add(btnReset);

        txtUserName.setFocusable(true);

        this.setSize(400, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        if (btn == btnRegister) {
            if (txtUserName.getText().equals("") || txtUserName.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "please input username", "empty username", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtPSW.getPassword().equals("")) {
                JOptionPane.showMessageDialog(this, "please input password", "empty password", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtConfirmedPSW.getPassword().equals("")) {
                JOptionPane.showMessageDialog(this, "please confirm password", "empty confirm password", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!String.valueOf(txtConfirmedPSW.getPassword()).equals(String.valueOf(txtPSW.getPassword()))) {
                JOptionPane.showMessageDialog(this, "password not equal", "confirm password failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String userName = null;
            String password = null;
            int i;

            userName = txtUserName.getText().trim();
            password = new String(txtPSW.getPassword());



            if (i < Data.customers.size()) {
                JOptionPane.showMessageDialog(this, "username exist", "failed", JOptionPane.ERROR_MESSAGE);
            } else {
                Data.customers.add(new Customer(userName, password));
                JOptionPane.showMessageDialog(this, "welcome, " + userName, "welcome",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Login();
            }
        } else if (btn == btnReset) {
            txtUserName.setText("");
            txtPSW.setText("");
            txtConfirmedPSW.setText("");
            txtUserName.setFocusable(true);
        }
    }
}