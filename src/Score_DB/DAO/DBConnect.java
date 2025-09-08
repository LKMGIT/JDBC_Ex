package Score_DB.DAO;

import Score_DB.Entity.Student;
import Score_DB.Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBConnect {
    // DB 커넥터 생성
    Connection conn = DBUtil.getConnection();

    //Insert sql 문 작성
    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO student_score (" +
                "name, language_score, english_score, math_score, science_score, " +
                "score_total, score_avg, score_grade) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            List<Integer> r = s.getRecode(); //
            ps.setString(1, s.getName());
            ps.setInt(2, r.get(0));  // language
            ps.setInt(3, r.get(1));  // english
            ps.setInt(4, r.get(2));  // math
            ps.setInt(5, r.get(3));  // science
            ps.setInt(6, s.getTotal());
            ps.setDouble(7, s.getAverage());
            ps.setString(8, s.getGrade());

            int n = ps.executeUpdate();
            System.out.println("[INSERT] rows=" + n);
        }
    }


}
