package client;

import java.util.ArrayList;

public class Customer {
    protected static String userName = null;
    protected String password = null;
    ArrayList<Integer> friends = null;

    public Customer() {
    }

    public Customer(String userName, String password) {
        Customer.userName = userName;
        this.password = password;
        this.friends = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            friends.add(i);
        }
    }

    public static String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        Customer.userName = userName;
    }

    public String getPSW() {
        return this.password;
    }

    public void setPSW(String password) {
        this.password = password;
    }

}
