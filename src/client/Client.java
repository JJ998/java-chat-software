package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends Thread{
	public static int SERVICE_PORT = 8080;
	public static String IP = "localhost";
	static String username = "";
	
	static PrintWriter pwtoserver = null;
	
	static JTextArea messages = new JTextArea();
	JTextField text = new JTextField();
	ActionListener sendListner = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = username + ": " + text.getText();
			messages.append(message + "\n");
			try {
				pwtoserver.println(message);
				pwtoserver.flush();
			}catch(Exception err) {
				messages.append("network error!\n");
			}
		}
	};
	
	public Client(String ip, int port, String name) {
		IP = ip;
		SERVICE_PORT = port;
		username = name;
	}
	
	public static void runClient() {
		System.out.println("正在向服务器请求连接……");
		Socket socket = null;
		Scanner inScanner = null;
		try {
			socket = new Socket(IP, SERVICE_PORT);
			inScanner = new Scanner(socket.getInputStream());
			System.out.println(inScanner.nextLine());
			pwtoserver = new PrintWriter(socket.getOutputStream());
			
			while(true) {
				String indata = inScanner.nextLine();
				messages.append(indata + "\n");
			}
		}catch(UnknownHostException e) {
			System.out.println("找不到服务器！");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		JFrame frame = new JFrame(username);
		frame.getContentPane().setLayout(new BorderLayout());
		messages.setEditable(false);
		
		JPanel textarea = new JPanel();
		textarea.setLayout(new BorderLayout());
	}


}
