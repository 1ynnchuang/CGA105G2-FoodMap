package tw.idv.anthony.web.member.controller;

import tw.idv.anthony.core.pojo.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.anthony.web.member.dao.MemberDao;
import tw.idv.anthony.web.member.entity.Member;
import tw.idv.anthony.web.member.service.MemberService;

import java.util.List;

//@RestController
@RequestMapping("member")
//下面的回應都會加上這段
public class MemberRestController {

	@Autowired
	private MemberService service;

	@Autowired
	private MemberDao dao;

	// 新增會員
	@PostMapping
	public Core create(@RequestBody Member member) {
		return service.register(member);
	}

	// 刪除會員
	@DeleteMapping("{id}")
	public Core delete(@PathVariable Integer id) {
		final boolean result = service.remove(id);
		Core core = new Core();
		core.setSuccessful(result);
		core.setMessage(result ? "刪除成功" : "刪除失敗");
		return core;
	}

	// 修改會員
	@PutMapping
	public Core update(@RequestBody Member member) {
		final boolean result = service.save(member);
		Core core = new Core();
		core.setSuccessful(result);
		core.setMessage(result ? "修改成功" : "修改失敗");
		return core;
	}

	// 查詢會員
	@GetMapping("{id}")
	public Member read(@PathVariable Integer id) {
		return dao.selectById(id);
	}

	// 查詢全部會員
	@GetMapping
	public List<Member> readAll() {
		return dao.selectAll();
	}
}
