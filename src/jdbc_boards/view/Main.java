package jdbc_boards.view;

import jdbc_boards.Controller.BoardMenu;
import jdbc_boards.vo.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BoardMenu boardMenu = new BoardMenu(); // 강한 결합

    public static void main(String[] args) throws IOException {
        printPage();
    }

    public static void printPage() throws IOException {
        boolean flag = true;
        while (flag) {
            int page = 0;
            System.out.println("");
            System.out.print("""
                    ============== [게시판 메뉴] ===============
                    
                        1. 글 작성             2. 내 글 수정
                        3. 글 삭제             4. 전체 글 조회
                        5. 선택 조회            6. 종료
                    
                    ===========================================
                    [번호를 입력하세요]: """);
            try {
                page = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력");
                continue;
            }

            switch (page) {
                case 1 -> insertBoard();
                case 2 -> updateBoard();
                case 3 -> deleteBoard();
                case 4 -> selectAllBoard();
                case 5 -> selectBoard();
                case 6 -> {
                    System.out.println("프로그램 종료");
                    flag = false;
                }
            }
        }

    }

    public static void insertBoard() throws IOException {
        Board board = new Board();

        while (true) {
            System.out.println("======== [글 작성] ========");

            System.out.print("글 제목: ");
            String btitle = br.readLine();

            if (btitle.length() > 100) {
                System.out.println("글 제목은 100자를 넘기면 안됩니다.");
                continue;
            } else {
                board.setBtitle(btitle);
            }

            System.out.println("[작성하실 글 내용 작성]");
            String bcontent = br.readLine();
            board.setBcontent(bcontent);

            System.out.println("작성자 이름: ");
            String bwriter = br.readLine();
            board.setBwriter(bwriter);

            board.setBdate(new Date());

            if (boardMenu.insertBoard(board) > 0) {
                System.out.println("저장 성공!");
                break;
            } else {
                System.out.println("저장 실패");
            }
        }
    }

    public static void deleteBoard() throws IOException {
        Board board = new Board();

        while (true) {
            System.out.println("======== [글 삭제] ========");

            System.out.print("작성자 이름: ");
            String bwriter = br.readLine();
            board.setBwriter(bwriter);

            System.out.print("삭제하실 글 번호");
            int bno = Integer.parseInt(br.readLine());
            board.setBno(bno);

            if (boardMenu.deleteBoard(board) > 0) {
                System.out.println("삭제 성공!");
                break;
            } else {
                System.out.println("삭제 실패");
            }

        }
    }

    public static void updateBoard() throws IOException {
        Board board = new Board();

        while (true) {
            System.out.println("======== [글 수정] ========");

            System.out.println("작성자 이름: ");
            String bwriter = br.readLine();
            board.setBwriter(bwriter);

            System.out.println("수정하실 글 번호");
            int bno = Integer.parseInt(br.readLine());
            board.setBno(bno);

            System.out.print("수정하실 글 제목: ");
            String btitle = br.readLine();
            board.setBtitle(btitle);

            System.out.println("[수정하실 글 내용]");
            String bcontent = br.readLine();
            board.setBcontent(bcontent);

            if (boardMenu.updateBoard(board) > 0) {
                System.out.println("수정 성공!");
                break;
            } else {
                System.out.println("수정 실패");
            }
        }


    }

    public static void selectBoard() throws IOException {
        Board board = null;

        while (true) {
            System.out.println("======== [글 조회] ========");
            System.out.print("조회할 글 번호: ");
            int bno = Integer.parseInt(br.readLine());

            board = boardMenu.selectBoard(bno);
            if (board != null) {
                System.out.println("=========[조회 글 내용]==========");
                System.out.println("[글 번호] " + board.getBno()
                        + " " + "[제목] " + board.getBtitle()
                        + " " + "[내용] " + board.getBcontent()
                        + " " + "[작성자] " + board.getBwriter()
                        + " " + "[작성일] " + board.getBdate());
                break;
            }else{
                System.out.println("조회 실패!");
            }

        }
    }

    public static void selectAllBoard() {
        List<Board> boards = new ArrayList<>();
        while (true) {
            System.out.println("========= [전체 글 조회] ==========");

            boards = boardMenu.selectAllBoard(boards);

            if (boards.size() > 0) {
                for (Board board : boards) {
                    System.out.println("[글 번호] " + board.getBno()
                            + " " + "[제목] " + board.getBtitle()
                            + " " + "[내용] " + board.getBcontent()
                            + " " + "[작성자] " + board.getBwriter()
                            + " " + "[작성일] " + board.getBdate());
                }
            }else{
                System.out.println("전체 조회 실패");
            }

            break;
        }
    }

}
