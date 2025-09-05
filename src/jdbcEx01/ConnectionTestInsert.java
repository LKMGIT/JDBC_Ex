package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionTestInsert {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "mysql1234";

        try{
            //드라이버 불러오기
            Class.forName(driver);
            System.out.println("Connecting to driver...");
        }catch(ClassNotFoundException e){
            System.out.println("Could not load driver");
        }
        // db 연결
        try(Connection con = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected to the database successfully");

            //매개변수화 된 sql 문
            String sql = "insert into person values(?,?)";
            //PrepparedStatement 얻기
            PreparedStatement pstmt = con.prepareStatement(sql);
            // 값 지정
            pstmt.setInt(1, 99);
            pstmt.setString(2,"둘리");

            //sql문 실행
            int result = pstmt.executeUpdate();

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
