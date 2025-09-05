package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountInsert {
    public static void main(String[] args) {
        // Connection 객체 생성
        Connection conn = DBUtil.getConnection();

        // sql문 작성
        String sql = "insert into accounts values(?,?,?)";

        //Pre뭐시기 작성
        try(PreparedStatement ps = conn.prepareStatement(sql) ){
            ps.setString(1, "1123-1231-1233-1231");
            ps.setString(2,"이경민");
            ps.setInt(3, 1000);

            ps.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("박");
        }


    }
}
