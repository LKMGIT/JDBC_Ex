package callableStatement.Model.MemberModel;

import callableStatement.Model.BaseModelInterface;
import callableStatement.VO.Member;

public interface Member_Interface extends BaseModelInterface<Member,String> {

    int update(Member member, int num);
}
