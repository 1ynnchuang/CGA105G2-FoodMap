package tw.idv.anthony.web.member.controller;

import tw.idv.anthony.core.pojo.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tw.idv.anthony.web.member.entity.Member;
import tw.idv.anthony.web.member.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("/member/manage")
public class ManageController {
	@Autowired
	private MemberService service;

	@GetMapping
	public String manage(Model model) {
		List<Member> memberList = service.findAll();
		model.addAttribute("memberList", memberList);
		return "/WEB-INF/member/manage.jsp";
	}

	@DeleteMapping("{id}")
	@ResponseBody
	public Core remove(@PathVariable Integer id) {
		final Core core = new Core();
		if (id == null) {
			core.setMessage("無id");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(service.remove(id));
		}
		return core;
	}

	@PutMapping
	@ResponseBody
	public Core save(@RequestBody Member member) {
		final Core core = new Core();
		if (member == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(service.save(member));
		}
		return core;
	}
}
