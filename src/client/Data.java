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
//    static String driver = "com.mysql.jdbc.Driver";
//    static String url = "jdbc:mysql://localhost:3306/chatdb";
    private static String user = "root";
    //    static String password = "19981114";
    private static String password = "981114";

    static void init() {
        customers.add(new Customer("ark", "123"));
    }

    private static void read(String table, String where) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
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

    public static Customer getCustomerbyId(Integer id) {
//        String where = "id=" + id.toString();
        Double randName = Math.random();
        return new Customer(randName.toString(), "1232");
    }

    public static Customer getCustomerbyUsername(String username) {
        String where = "username=\"" + username + "\"";
        try {
            String password = rs.getString("password");
            String friends = rs.getString("friends");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Double randName = Math.random();
        return new Customer(randName.toString(), "1232");
    }
}
