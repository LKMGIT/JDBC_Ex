package jdbc_boards.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Board {
    private int bno;            /// 게시물 번호
    private String btitle;      /// 게시물 제목
    private String bcontent;    /// 게시물 내용
    private String bwriter;     /// 게시물 작성자
    private Date bdate;         /// 게시물 작성 날짜
}
