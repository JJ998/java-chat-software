package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Data {
	
	//����Connection����
    Connection con;
    //����������
    String driver = "com.mysql.jdbc.Driver";
    //URLָ��Ҫ���ʵ����ݿ���mydata
    String url = "jdbc:mysql://localhost:3306/chatdb";
    //MySQL����ʱ���û���
    String user = "root";
    //MySQL����ʱ������
    String password = "19981114";
	
	public static List<Customer> customers = new ArrayList<Customer>();
	
	public static void init() {
		customers.add(new Customer("chf","2333"));
	}
	
	public void read() {
		try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = con.createStatement();
            
            con.close();
        } catch(ClassNotFoundException e) {   
            //���ݿ��������쳣����
            System.out.println("Sorry,can\'t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("Read database complete.");
        }
	}
	
	public Customer getCustomerbyId(Integer id) {
		Customer customer = new Customer("xxx", "1232");
		return customer;
	}
}
