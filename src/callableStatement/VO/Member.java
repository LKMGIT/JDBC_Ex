package callableStatement.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private int seq;
    private String m_id;
    private String m_password;
    private String m_email;
    private String m_hp;
    private Date m_registdate;
    private int m_point;
}
