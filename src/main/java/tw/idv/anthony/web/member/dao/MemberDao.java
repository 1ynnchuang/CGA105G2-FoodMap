package tw.idv.anthony.web.member.dao;


import tw.idv.anthony.core.dao.CoreDao;
import tw.idv.anthony.web.member.entity.Member;

public interface MemberDao extends CoreDao<Member, Integer> {
	
	Member selectByUsername(String username);
	
	Member selectForLogin(String username, String password);
}
