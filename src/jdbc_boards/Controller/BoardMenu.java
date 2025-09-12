package jdbc_boards.Controller;

import jdbc_boards.model.BoardDAO;
import jdbc_boards.vo.Board;

import java.awt.event.ActionListener;
import java.util.List;

public class BoardMenu implements BoardContoroller_Intreface{
    BoardDAO boardDAO = new BoardDAO();

    @Override
    public int insertBoard(Board board) {
        return boardDAO.insertBoard(board);
    }

    @Override
    public int updateBoard(Board board) {
        return boardDAO.updateBoard(board);
    }

    @Override
    public int deleteBoard(Board board) {
        return boardDAO.deleteBoard(board);
    }

    @Override
    public List<Board> selectAllBoard(List<Board> boards) {
        return boardDAO.selectAllBoard(boards);
    }

    @Override
    public Board selectBoard(int id) {
        return boardDAO.selectBoard(id);
    }
}
