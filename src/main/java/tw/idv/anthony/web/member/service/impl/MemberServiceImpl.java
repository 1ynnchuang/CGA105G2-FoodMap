package tw.idv.anthony.web.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.anthony.web.member.dao.MemberRepository;
import tw.idv.anthony.web.member.mapper.MemberMapper;
import tw.idv.anthony.web.member.service.MemberService;
import tw.idv.anthony.web.member.dao.MemberDao;
import tw.idv.anthony.web.member.entity.Member;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
//	private MemberDao dao;
	private MemberRepository dao;

	@Autowired
	private MemberMapper memberMapper;

	

	@Override
	public Member register(Member member) {
//			beginTransaction();
		if (member.getUsername() == null) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
			throw new RuntimeException("帳號未輸入");
		}

		if (member.getPassword() == null) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
			throw new RuntimeException("密碼未輸入");
		}

		if (member.getNickname() == null) {
			member.setMessage("暱稱未輸入");
			member.setSuccessful(false);
			throw new RuntimeException("暱稱未輸入");
		}

		if (dao.getByUsername(member.getUsername()) != null) {
			member.setMessage("帳號重複");
			member.setSuccessful(false);
			throw new RuntimeException("帳號重複");
		}

		member.setRoleId(2);
		member = dao.save(member);
//		確認
		if (member.getId()==null) {
			member.setMessage("註冊錯誤，請聯絡客服!");
			member.setSuccessful(false);
			throw new RuntimeException("註冊錯誤，請聯絡客服!");
		}
		member.setMessage("註冊成功");
		member.setSuccessful(true);
		return member;

	}
	@Transactional
	@Override
	public Member login(Member member) {
		final String username = member.getUsername();
		final String password = member.getPassword();

		if (username == null) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
			return member;
		}

		if (password == null) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
			return member;
		}

		member = dao.getForLogin(username, password);
		if (member == null) {
			member = new Member();
			member.setMessage("帳號或密碼錯誤");
			member.setSuccessful(false);
			return member;
		}

		member.setMessage("登入成功");
		member.setSuccessful(true);
		return member;
	}

	@Transactional
	@Override
	public Member edit(Member member) {
		final Member oMember = dao.getByUsername(member.getUsername());
		member.setPass(oMember.getPass());
		member.setRoleId(oMember.getRoleId());
		member.setUpdater(member.getUsername());
		final int resultCount = dao.update(member);
		member.setSuccessful(resultCount > 0);
		member.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return member;
	}

	@Override
	public List<Member> findAll() {
		return memberMapper.selectAll();
	}
	@Transactional
	@Override
	public boolean remove(Integer id) {
		Member member=dao.getOne(id);
//		beginTransaction();
//		確認
		try {
			dao.deleteById(id);
			return true;
		}catch (Exception e){
			return false;
		}
	}
	@Transactional
	@Override
	public boolean save(Member member) {
		return dao.update(member) > 0;
	}

}
