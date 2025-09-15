package callableStatement.Controller.Member_Controller;

import callableStatement.Model.MemberModel.Member_DAO;
import callableStatement.VO.Member;

import java.util.List;

public class Member_Controller_Impl implements Membre_Controller {
    Member_DAO member_DAO;

    @Override
    public int update(Member member, int num) {
        return member_DAO.update(member,num);
    }

    @Override
    public int insert(Member entity) {
        return member_DAO.insert(entity);
    }

    @Override
    public int update(Member entity) {
        return 0;
    }

    @Override
    public int delete(Member entity) {
        return member_DAO.delete(entity);
    }

    @Override
    public List<Member> findAll() {
        return member_DAO.selectAll();
    }

    @Override
    public Member findById(String s) {
        return member_DAO.selectById(s);
    }
}
