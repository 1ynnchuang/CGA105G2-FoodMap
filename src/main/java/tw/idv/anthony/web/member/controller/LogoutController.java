package tw.idv.anthony.web.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServlet;

@RestController
@RequestMapping("/member/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@GetMapping
	public void logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
	}
//	sessionStatus可以用來關閉HTTP session，並清除，有執行緒結束的感覺。
}
