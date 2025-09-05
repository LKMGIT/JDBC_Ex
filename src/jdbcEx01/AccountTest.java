package jdbcEx01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class AccountTest {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "mysql1234";
        String sql = "insert into accounts(ano, owner, balance) values(?,?,?)";


        String selectsql = "select * from accounts where owner ='홍길동'";

        try {
            //드라이버 불러오기
            Class.forName(driver);
            System.out.println("Connecting to driver...");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver");
        }
        // db 연결
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database successfully");

            //매개변수화 된 sql 문
            //PrepparedStatement 얻기
            PreparedStatement pstmt = con.prepareStatement(sql);

            // 값 지정
            pstmt.setString(1, "1111-1212-3232-1215");
            pstmt.setString(2, "홍길동e");
            pstmt.setInt(3, 123123);

            //sql문 실행
            int result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("입금성공");
            } else {
                System.out.println("입금 실패!");
            }

            PreparedStatement select_pstmt = con.prepareStatement(selectsql);
            ResultSet rs2 = select_pstmt.executeQuery();

            while (rs2.next()) {
                System.out.println("계좌번호 : " + rs2.getString(1));
                System.out.println("계좌주 : " + rs2.getString(2));
                System.out.println("계좌총액 : " + rs2.getString(3));
            }

            select_pstmt.close();

        } catch (Exception e) {
            System.out.println("Error occured while connecting to the database");
            e.printStackTrace();
        }

    }
}
