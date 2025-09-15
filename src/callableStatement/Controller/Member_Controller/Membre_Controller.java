package callableStatement.Controller.Member_Controller;

import callableStatement.Controller.BaseControllerInterface;
import callableStatement.VO.Member;


public interface Membre_Controller extends BaseControllerInterface<Member,String> {
    public int update(Member member, int num);
}
