package jdbcEx02;

//UPDATE 테이블명 SET(필드 = '수정값') WHERE num = 1;
// String sql = UPDATE person SET id = ? , name = ? Where num = ?

// String sql = new StringBuilder()
//              .append("Update person").append("Set id = ?").append(", name = ?"). append("Where num = ?").toString();

import jdbcEx01.vo.Person;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonUpdateEx {
    public static void main(String[] args) {

        Connection con = DBUtil.getConnection();

        //매개변수화 된 UPDATE SQL문 작성
        String sql = new StringBuilder().append("UPDATE person SET")
                .append(" id = ?, ")
                .append("name = ? ")
                .append("WHERE num = ?")
                .toString();


        //PreparedStatement 객체 생성 하고 값 지정
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 150);
            pstmt.setString(2,"도우너");
            pstmt.setInt(3, 1);

            //sql 문 실행
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " rows updated");

            String select = "select id, name from person";
            ResultSet rs = pstmt.executeQuery(select);
            while (rs.next()) {
                jdbcEx01.vo.Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                System.out.println(person.getId() + " " + person.getName());
            }

        }catch(SQLException e){
            System.out.println("뷁");
            e.printStackTrace();
        }

    }
}
