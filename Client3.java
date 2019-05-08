package v3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client3 {

	public static void main(String args[]) {
		System.out.println("client is on");
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try {
			ds = new DatagramSocket(8080);
			byte[] b = new byte[1024];
			while(true) {
				dp = new DatagramPacket(b, b.length);
				ds.receive(dp);
				System.out.println(new String(b));
			}
		} catch (SocketException e) {
			System.out.println("net error!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ds.close();
	}
}
