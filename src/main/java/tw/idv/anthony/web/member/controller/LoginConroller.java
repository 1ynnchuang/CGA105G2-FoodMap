package tw.idv.anthony.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.anthony.web.member.entity.Member;
import tw.idv.anthony.web.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member/login")
public class LoginConroller {
	@Autowired
	private MemberService service;

	@GetMapping("{username}/{password}")
//	get傳的，不是JSON
	public Member login(HttpServletRequest request, @PathVariable String username, @PathVariable String password) {
		Member member = new Member();
		if (username == null || password == null) {
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			return member;
		}
		member.setUsername(username);
		member.setPassword(password);
		member = service.login(member);
		if (member.isSuccessful()) {
//			getSession(false):如果參數帶false，當getSession()=null時候，不會new session
			if (request.getSession(false) != null) {
//				Session的址
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
		}
		return member;
	}

}
