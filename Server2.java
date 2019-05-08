package v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Server2 {
	public static final int SERVICE_PORT = 8080;
	static PrintWriter pwtoclien = null;
	static Scanner inScanner = null;
	
	static void runServer() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(SERVICE_PORT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("�������Ѵ�");
		while(true) {
			try {
				Socket socket = ss.accept();
				System.out.println(socket.getInetAddress()+"�ѳɹ����ӵ���̨�������ϡ�");
				pwtoclien = new PrintWriter(socket.getOutputStream());
				pwtoclien.println("�����: ������");
				pwtoclien.flush();
				ObjectInputStream ooi = new ObjectInputStream(socket.getInputStream());
				User user;
				try {
					user = (User) ooi.readObject();
					System.out.println(user);
				} catch (ClassNotFoundException e) {
					pwtoclien.println("User error!");
				}
				
				try {
					inScanner = new Scanner(socket.getInputStream());
					//�����ȴ��ͻ��˷�����Ϣ����
					while(inScanner.hasNextLine()){
						String indata = inScanner.nextLine();
						System.out.println(indata);
						pwtoclien = new PrintWriter(socket.getOutputStream());
						pwtoclien.println("�����: ok");
						pwtoclien.flush();
					}
				} catch(IOException e1) {
					e1.printStackTrace();
				} finally {
					try{
						pwtoclien.close();
						inScanner.close();
					} catch(Exception e2) {
						e2.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		runServer();
	}

}