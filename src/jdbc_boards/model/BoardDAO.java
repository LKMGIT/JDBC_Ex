package jdbc_boards.model;

import jdbc_boards.Util.Bookdb_util;
import jdbc_boards.vo.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BoardDAO implements Board_DAO_Interface {

    //데이터 DB에 저장하기
    @Override
    public int insertBoard(Board board) {
        String sql = "insert into boardtable (btitle, bcontent, bwriter, bdate) values(?,?,?,now())";

        try (Connection conn = Bookdb_util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, board.getBtitle());
            ps.setString(2, board.getBcontent());
            ps.setString(3, board.getBwriter());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateBoard(Board board) {
        String sql = "update boardtable set btitle=?, bcontent=?, bdate=now() where bno=? and bwriter=?";

        try (Connection conn = Bookdb_util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, board.getBtitle());
            ps.setString(2, board.getBcontent());
            ps.setInt(3, board.getBno());
            ps.setString(4, board.getBwriter());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteBoard(Board board) {
        String sql = "delete from boardtable where bno=? and bwriter=?";

        try (Connection conn = Bookdb_util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, board.getBno());
            ps.setString(2, board.getBwriter());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Board> selectAllBoard(List<Board> boards) {
        String sql = "SELECT * FROM boardtable ORDER BY bno";

        try (Connection conn = Bookdb_util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Board board = new Board();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));
                boards.add(board);
            }
            // 번호 순서대로 정렬된 리스트를 반환
            return boards;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board selectBoard(int id) {
        Board board = new Board();

        String sql = "select * from boardtable where bno=?";

        try (Connection conn = Bookdb_util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));
            }
            return board;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
