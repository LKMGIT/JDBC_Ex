package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionEx {
    public static void main(String[] args) {
        // database 와 연결하기 위한 단계ㅖ
        Connection con = null;
        String url =
                "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        /*
                        + "?useSSL=false&allowPublicKeyRetrieval=true"
                        + "&useUnicode=true&characterEncoding=UTF-8"
                        + "&serverTimezone=Asia/Seoul
         */
        String user = "root";
        String password = "mysql1234";

        try {
            // 1. 드라이버 로드 ( 접속하고 싶은 DB의 드라이버를 찾아서 load 해와야한다.)
            Class.forName("com.mysql.cj.jdbc.Driver"); // 리플렉션 기법.
            System.out.println("driver loaded");

            // 2. 드라이버 로드를 성공하였다면, 연결 Connection 객체를 생성.
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connection established" + con);

            // 3. Connection 객체가 생성 되었다면, 쿼리문을 담아 statements 객체에 담아 DB에게ㅔ 전송한다

            // 4. 전송한 결과를 받아서 처리한다/


        } catch (Exception e) {
            System.out.println("failed to connect to database");
        } finally {
            // 다 사용했으면 연결 종료
            if(con != null) {
                try{
                    con.close();
                    System.out.println("connection closed");
                }catch(SQLException e){
                    System.out.println("failed to close connection");
                }
            }
        }
    }
}
