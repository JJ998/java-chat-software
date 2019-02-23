package client;

import java.util.ArrayList;

public class Customer {
	protected static String userName = null;
    protected String password = null;
    ArrayList<Integer> friends = null;

    public Customer() {
    }

    public Customer(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.friends = new ArrayList<Integer>();
        friends.add(1);
        friends.add(2);
        friends.add(3);
    }
     
    public static String getUserName() {
        return userName;
    }
     
    public void setUserName(String userName) {
        this.userName = userName;
    }
     
    public String getPSW() {
        return this.password;
    }
     
    public void setPSW(String password) {
        this.password = password;
    }

}
