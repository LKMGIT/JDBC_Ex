package jdbc_boards.model;

import jdbc_boards.vo.Board;

import java.util.List;

public interface Board_DAO_Interface {
    public int insertBoard(Board board);
    public int updateBoard(Board board);
    public int deleteBoard(Board board);
    public List<Board> selectAllBoard(List<Board> boards);
    public Board selectBoard(int id);
}
