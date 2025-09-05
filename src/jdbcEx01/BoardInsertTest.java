package jdbcEx01;

import java.io.FileInputStream;
import java.sql.*;

public class BoardInsertTest {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "mysql1234";
        String sql = "insert into boards(btitle, bcontent, bwriter, bdate, bfilename, bfiledata) values(?,?,?,now(),?,?)";
        String selectsql = "select * from boards where bno = 1";

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
            //PrepparedStatement 얻기
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 값 지정
            pstmt.setString(1,"안녕하세요");
            pstmt.setString(2,"반갑습니다.");
            pstmt.setString(3, "이경민");
            pstmt.setString(4, "spring.jpg");
            pstmt.setBlob(5, new FileInputStream("C:/Temp/spring.jpg"));

            //sql문 실행
            int result = pstmt.executeUpdate();

            if(result == 1){
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()){
                    int bno = rs.getInt(1);
                    System.out.println("bno 값은 = " + bno);
                }
                rs.close();
            }

           PreparedStatement pstmt2 = con.prepareStatement(selectsql);
            ResultSet rs2 = pstmt2.executeQuery();

            while(rs2.next()){
                int bno = rs2.getInt(1);
                String btitle = rs2.getString(2);
                String bcontent = rs2.getString(3);
                String bwriter = rs2.getString(4);
                String bdate = rs2.getString(5);
                String bfilename = rs2.getString(6);
                Blob bfiledata = rs2.getBlob(7);

                System.out.println("bno = " + bno);
                System.out.println("btitle = " + btitle);
                System.out.println("bcontent = " + bcontent);
                System.out.println("bwriter = " + bwriter);
                System.out.println("bdate = " + bdate);
                System.out.println("bfilename = " + bfilename);
                System.out.println("bfiledata = " + bfiledata);
            }
            pstmt2.close();

        }catch(Exception e){
            System.out.println("Error occured while connecting to the database");
        }

    }
}
