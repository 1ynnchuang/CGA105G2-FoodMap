//package web.member.dao.impl;
//
////import static core.util.CommonUtil.getConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//import org.hibernate.query.Query;
//
//import web.member.dao.MemberDao;
//import web.member.entity.Member;
//
//public class MemberDaoImpl3 implements MemberDao {
//
//	@Override
//	public int insert(Member member) {
//
//		getSession().persist(member);
//		return 1;
//	}
//
//	@Override
//	public int deleteById(Integer id) {
//		Member member = new Member();
//		member.setId(id);
//		getSession().remove(member);
//		return 1;
//		
//	}
//
//	@Override
//	public int update(Member member) {
//		
//		final StringBuilder hql = new StringBuilder().append("update MEMBER set ");
//		final String password = member.getPassword();
//		if (password != null && !password.isEmpty()) {
//			hql.append("PASSWORD = :password,");
//		}
//		hql.append("NICKNAME = :nickname,")
//		.append("PASS = :pass,")
//		.append("ROLE_ID = :roleId,")
//		.append("UPDATER = :updater,")
//		.append("LAST_UPDATED_DATE = NOW() ")
//		.append("where USERNAME = :username");
//		
//		Query<?> query = getSession().createQuery(hql.toString())
//		.setParameter("nickname", member.getNickname())
//		.setParameter("pass", member.getPass())
//		.setParameter("roleId", member.getRoleId())
//		.setParameter("updater", member.getUpdater())
//		.setParameter("username", member.getUsername());		
//		if(password != null && !password.isEmpty()) {
//			query.setParameter("password", password);
//		}
//			return query.executeUpdate();
//	}
//
//	@Override
//	public Member selectById(Integer id) {
//		
//		
//		return getSession().get(Member.class,id);
//	}
//
//	@Override
//	public List<Member> selectAll() {
//		final String hql = "From Member order by id";
//		return getSession().createQuery(hql,Member.class).getResultList();
//		
//		
//	}
//
//	@Override
//	public Member selectByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Member selectForLogin(String username, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
