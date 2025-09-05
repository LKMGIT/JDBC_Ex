package jdbcEx02;

/*
JDBC를 이용해서 DELETE 문을 실행하는 방법

DELETE from 테이블명;  현재 테이블에 있는 모든 로드를 전부 삭제를함
DELETE from 테이블명 WHERE id(pk) = 식별값; 구분할 수 있는 값으로 해당 부분만 삭제하도록 해주는게 안전
DELETE form person Where num = 1;   pk(num) 이 1인 행을 삭제한다.

String sql = "DELETE FROM person WHERE num = ?"


 */


import jdbcEx01.vo.Person;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonDeleteEx {
    public static void main(String[] args) {

        //Connection 객체 생성
        Connection conn = DBUtil.getConnection();

        // sql 쿼리문 작성
        String sql = "delete from person where num = ?";

        //perp뭐시기 만들기
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            int rows = pstmt.executeUpdate();
            System.out.println(rows);

            String select = "select id, name from person";
            ResultSet rs = pstmt.executeQuery(select);
            while (rs.next()) {
                jdbcEx01.vo.Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                System.out.println(person.getId() + " " + person.getName());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
