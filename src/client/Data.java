package client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Data {

    static List<Customer> customers = new ArrayList<Customer>();
    private static Connection con;
    private static ResultSet rs;
    // mysql8+
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/chatdb?useSSL=FALSE&serverTimezone=UTC";
    // mysql5.x
    /*static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/chatdb";*/
    private static String user = "root";
    //    static String password = "19981114";
    private static String password = "981114";

    static void init() {
        customers.add(new Customer("ark", "123", null));
    }
    static void exit() {
        try {
            if (!con.isClosed())
                con.close();
            if (!rs.isClosed())
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void read(String table, String where) {
        try {
            if (!con.isClosed() || con == null) {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, password);
            }
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String readSQL = "select * from " + table + " where " + where;
            System.out.println("execute: " + readSQL);
            rs = statement.executeQuery(readSQL);
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can\'t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean write(PreparedStatement psql) {
        try {
            if (!con.isClosed() || con == null) {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, password);
            }
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            psql.executeUpdate(); // 执行更新
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can\'t find the Driver!");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Customer getCustomerbyId(Integer id) {
//        String where = "id=" + id.toString();
        Double randName = Math.random();
        return new Customer(randName.toString(), "1232", null);
    }

    public static Customer getCustomerbyUsername(String username) {
        String where = "username=\"" + username + "\"";
        try {
            String password = rs.getString("password");
            String friends = rs.getString("friends");
            if (!password.isEmpty() && !friends.isEmpty()) return new Customer(username, null, friends);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static boolean addCustomer(Customer customer) {
        PreparedStatement psql;
        try {
            psql = con.prepareStatement("insert into users (username, password, friends) "
                    + "values(?, ?, ?)");
            psql.setString(1, customer.getUserName());
            psql.setString(2, customer.getPSW());
            ArrayList<Integer> friends = customer.getFriends();
            String friendsList = "";
            for(Integer tmp:friends)
                friendsList = friendsList + tmp + ",";
            psql.setString(3, friendsList.substring(0, friendsList.lastIndexOf(",")-1));
            return write(psql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
