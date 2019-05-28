package client;

import java.util.ArrayList;

public class Customer {
    private int id;
    protected String userName = null;
    protected String password = null;
    ArrayList<Integer> friends = null;

    public Customer() {
    }

    public Customer(String userName, String password, String friendsList) {
        this.userName = userName;
        this.password = password;
        if (friendsList != null) {
            String[] friendList = friendsList.split(",");
            this.friends = new ArrayList<>();
            for (String friend : friendList)
                friends.add(Integer.valueOf(friend));
        }
    }

    public String getUserName() {
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

    int getId() { return id; }

    void setId(int id) {
        this.id = id;
    }

    ArrayList<Integer> getFriends() { return friends; }

}
