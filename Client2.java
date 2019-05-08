package v2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

public class Client2 extends Thread{
	public static int SERVICE_PORT = 8080;
	public static String IP = "localhost";
	static User user = null;
	
	static FileOutputStream serverfop = null;
	static PrintWriter pwtoserver = null;
	
	static JTextArea messages = new JTextArea();
	JTextField text = new JTextField();
	ActionListener sendListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String message = user.username + ": " + text.getText();
			messages.append(message + "\n");
			try{
				pwtoserver.println(message);
				pwtoserver.flush();
			} catch(Exception err) {
				System.out.println(err.toString());
				messages.append("network error!\n");
			}
		}
		
	};
	ActionListener fileChoserListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fd = new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			if(f != null) {
				try {
					FileInputStream file = new FileInputStream(f);
					byte[] b = new byte[64];
					int s = file.read(b);
					while(s != -1) {
						serverfop.write(b);
						s = file.read(b);
					}
					messages.append(user.username + ": " + f.getName() + "√\n");
				} catch (FileNotFoundException e1) {
					messages.append("file no found!\n");
				} catch (IOException e1) {
					messages.append("error reading file!\n");
				}
			}
		}
		
	};
	
	public Client2() { }
	public Client2(String ip, int port, User u) {
		IP = ip;
		SERVICE_PORT = port;
		user = u;
	}
	
	public static void runClient(){
		System.out.println("正在向服务器请求连接。。。");
		Socket socket = null;
		Scanner inScanner = null;
		try {
			socket = new Socket(IP, SERVICE_PORT);
			serverfop = (FileOutputStream) socket.getOutputStream();
			inScanner = new Scanner(socket.getInputStream()); 
			System.out.println(inScanner.nextLine());
			pwtoserver = new PrintWriter(socket.getOutputStream());
			ObjectOutputStream ooo = new ObjectOutputStream(socket.getOutputStream());
			ooo.writeObject(user);
			//ooo.close();
			
			while(true) {
				String indata = inScanner.nextLine();
				messages.append(indata + "\n");
			}
		} catch (UnknownHostException e) {
			System.out.println("找不到服务器！");
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		JFrame frame = new JFrame(user.username);
		frame.getContentPane().setLayout(new BorderLayout());
		messages.setEditable(false);
		
		JPanel textarea = new JPanel();
		textarea.setLayout(new BorderLayout());
		JButton sendButton = new JButton("发送");
		sendButton.addActionListener(sendListener);
		JButton choseFile = new JButton("选择文件");
		choseFile.addActionListener(fileChoserListener);
		textarea.add(text, BorderLayout.CENTER);
		textarea.add(choseFile, BorderLayout.WEST);
		textarea.add(sendButton, BorderLayout.EAST);
		
		JScrollPane textPane = new JScrollPane();
		textPane.setViewportView(messages);
		textPane.setBounds(0, 187, 591, 98);
		frame.add(textPane, BorderLayout.CENTER);
		frame.add(textarea, BorderLayout.SOUTH);
		frame.setSize(280, 400);
		
		frame.setVisible(true);
		
		runClient();
	}
	
	public static void main(String args[]) {
		Client2 client = new Client2("localhost", 8080, new User("Client A", 1, 20));
		client.start();
	}
}
