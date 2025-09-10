package Score_DB.DAO;

import Score_DB.Entity.Student;
import Score_DB.Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBConnect {
    //Insert sql 문 작성
    public void insert(Student s) throws SQLException {
        String sql = "INSERT INTO student_score (" +
                "name, language_score, english_score, math_score, science_score, " +
                "score_total, score_avg, score_grade) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            List<Integer> r = s.getRecode(); //리스트 받아오기
            ps.setString(1, s.getName());
            ps.setInt(2, r.get(0));  // 국어
            ps.setInt(3, r.get(1));  // 영어
            ps.setInt(4, r.get(2));  // 수학
            ps.setInt(5, r.get(3));  // 과학
            ps.setInt(6, s.getTotal()); //총점
            ps.setDouble(7, s.getAverage()); //평균
            ps.setString(8, s.getGrade()); // 학점

            int n = ps.executeUpdate();
            System.out.println("[INSERT] rows=" + n); // 확인 문구
        }
    }


}
