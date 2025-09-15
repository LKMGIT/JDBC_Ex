package callableStatementEx;

import jdbc_boards.Util.Bookdb_util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Member {

    static Connection conn = Bookdb_util.getConnection();

    public static void main(String[] args) throws SQLException {

        selectAllMember();



    }

    public static void insertMember() throws SQLException {
        String m_userid= "userid";
        String m_password= "password";
        String m_email= "name@email.com";
        String m_hp = "010-0202-2002";
        String result = null;
        String sql = "{CALL SP_MEMBER_INSERT(?,?,?,?,?)}";

        try(CallableStatement call = conn.prepareCall(sql)){
            call.setString(1, m_userid);
            call.setString(2, m_password);
            call.setString(3, m_email);
            call.setString(4, m_hp);

            call.registerOutParameter(5, java.sql.Types.INTEGER);

            call.execute();

            result = String.valueOf(call.getInt(5));

            if(result.equals("100")){
                System.out.println("이미 가입된 사용자");
            }else if (result.equals("200")){
                System.out.println("가입 성공 ");
            }
        }
    }

    public static void selectAllMember() throws SQLException {
        String sql = "{call SP_MEMBER_LIST}";

        try(CallableStatement call = conn.prepareCall(sql)){
            call.execute();
            ResultSet rs = call.getResultSet();

            while(rs.next()){
                System.out.println("m_seq: " + rs.getInt(1)
                + "\t" + " m_userid: " + rs.getString(2)
                + "\t" + " m_password: " + rs.getString(3)
                + "\t" + " m_email: " + rs.getString(4)
                + "\t" + " m_hp: " + rs.getString(5)
                + "\t" + " m_date: " + rs.getString(6));
            }
        }
    }

    public static void deleteMember() throws SQLException {
        String sql = "{CALL SP_DELETE}";

    }
}
