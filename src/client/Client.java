package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static int SERVICE_PORT = 8080;
    public static String IP = "localhost";
    static PrintWriter pwtoserver = null;

    public static void main(String[] args) {
        Data.init();
        Login login = new Login();
    }

    public void run() {
        System.out.println("Connecting to server...");
        Socket socket = null;
        Scanner inScanner = null;
        try {
            socket = new Socket(IP, SERVICE_PORT);
            inScanner = new Scanner(socket.getInputStream());
            System.out.println(inScanner.nextLine());
            pwtoserver = new PrintWriter(socket.getOutputStream());

            while (true) {
                String indata = inScanner.nextLine();
            }
        } catch (UnknownHostException e) {
            System.out.println("couldn\'t find server!");
            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
