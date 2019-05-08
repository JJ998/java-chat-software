package v3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server3 extends Thread{

	public void run() {
		System.out.println("server is on");
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try {
			ds = new DatagramSocket(8181);
			while(true) {
				String ch = Character.toString((char) ((int)(Math.random() * 26) + 'a'));
				System.out.println(ch);
				dp = new DatagramPacket(ch.getBytes(), ch.length(),
						InetAddress.getByName("127.0.0.1"), 8080);
				ds.send(dp);
				try {
					sleep(5000);
				} catch (InterruptedException e) { }
			}
		} catch (SocketException e) {
			System.out.println("net error!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ds.close();
	}
	
	public static void main(String args[]) {
		Server3 server = new Server3();
		server.start();
	}
}
