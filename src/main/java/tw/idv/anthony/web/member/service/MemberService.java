package tw.idv.anthony.web.member.service;

import tw.idv.anthony.core.service.CoreService;
import tw.idv.anthony.web.member.entity.Member;

import java.util.List;

public interface MemberService extends CoreService{

	Member register(Member member);

	Member login(Member member);
	
	Member edit(Member member);
	
	List<Member> findAll();
	
	boolean remove(Integer id);
	
	boolean save(Member member);
}