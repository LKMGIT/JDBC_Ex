package callableStatement.Model.MemberModel;

import callableStatement.VO.Member;
import jdbc_boards.Util.Bookdb_util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Member_DAO implements Member_Interface {

    static Connection conn = Bookdb_util.getConnection();

    @Override
    public int insert(Member member) {
        String sql = "{call SP_MEMBER_INSERT(?,?,?,?,?)}";

        try (CallableStatement cal = conn.prepareCall(sql)) {
            cal.setString(1, member.getM_id());
            cal.setString(2, member.getM_password());
            cal.setString(3, member.getM_email());
            cal.setString(4, member.getM_hp());

            cal.registerOutParameter(5, Types.INTEGER);

            cal.execute();

            return cal.getInt(5);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Member entity) {
        return 0;
    }

    @Override
    public int update(Member member, int num) {
        String sql = "{ call SP_UPDATE(?, ?, ?) }";
        try (CallableStatement cal = conn.prepareCall(sql)) {
            cal.setInt(1, num);
            cal.setString(3, member.getM_id());
            // num에 따라 보낼 값 선택
            String value = switch (num) {
                case 1 -> member.getM_password();
                case 2 -> member.getM_email();
                case 3 -> member.getM_hp();
                default -> throw new IllegalStateException("Unexpected value: " + num);
            };
            cal.setString(2, value);

            return cal.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int delete(Member member) {
        String sql = "{call SP_DELETE(?, ?)} }";
        try (CallableStatement cal = conn.prepareCall(sql)) {

            cal.setString(1, member.getM_id());
            cal.setString(2, member.getM_password());

            return cal.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List selectAll() {
        List<Member> list = new ArrayList<Member>();
        String sql = "{call SP_MEMBER_LIST()}";

        try(CallableStatement cal = conn.prepareCall(sql)){

            ResultSet rs = cal.getResultSet();

            while(rs.next()){
                Member member = new Member();
                member.setSeq(rs.getInt(1));
                member.setM_id(rs.getString(2));
                member.setM_password(rs.getString(3));
                member.setM_email(rs.getString(4));
                member.setM_hp(rs.getString(5));
                member.setM_registdate(rs.getDate(6));
                member.setM_point(rs.getInt(7));
                list.add(member);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public Member selectById(String u_id) {
        return null;
    }
}
