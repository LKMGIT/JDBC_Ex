package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDelete {
    public static void main(String[] args) {

        Connection con = DBUtil.getConnection();

        String sql = "DELETE FROM accounts where owner = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"이경민");

            pstmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
