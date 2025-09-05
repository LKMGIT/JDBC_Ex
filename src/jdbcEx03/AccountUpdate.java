package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountUpdate {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();

        String sql = "UPDATE accounts SET balance = ? WHERE owner = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,100);
            pstmt.setString(2,"이경민");

            pstmt.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
