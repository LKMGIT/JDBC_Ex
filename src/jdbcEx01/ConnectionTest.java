package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "mysql1234";

        try{
            Class.forName(driver);
            System.out.println("Connecting to driver...");
        }catch(ClassNotFoundException e){
            System.out.println("Could not load driver");
        }
        try(Connection con = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected to the database successfully");

            String sql = "insert into person values(100,'홍길동')";

            int result = con.createStatement().executeUpdate(sql);

            if(result == 1){
                System.out.println("Inserted successfully");
            }else{
                System.out.println("Insertion failed");
            }

        }catch(Exception e){
            System.out.println("Error occured while connecting to the database");
        }

    }
}
