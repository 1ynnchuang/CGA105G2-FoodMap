package tw.idv.anthony.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.anthony.web.member.entity.Member;
import tw.idv.anthony.web.member.service.MemberService;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("member/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MemberService service;

	@PostMapping
	public Member register(@RequestBody Member member) {
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			return member;
		}
		return service.register(member);
	}

}
