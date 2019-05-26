package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Customer> customers = new ArrayList<Customer>();
    Connection con;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/chatdb";
    String user = "root";
    String password = "19981114";

    public static void init() {
        customers.add(new Customer("chf", "2333"));
    }

    public void read() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();

            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can\'t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Read database complete.");
        }
    }

    public Customer getCustomerbyId(Integer id) {
        Double randName = Math.random();
        return new Customer(randName.toString(), "1232");
    }
}
