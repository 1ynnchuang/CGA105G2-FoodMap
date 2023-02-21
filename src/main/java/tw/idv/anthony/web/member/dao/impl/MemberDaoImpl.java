package tw.idv.anthony.web.member.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.idv.anthony.web.member.dao.MemberDao;
import tw.idv.anthony.web.member.entity.Member;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {
//	注入session物件
	@PersistenceContext
	private Session session;
	
	@Override
	public int insert(Member member) {
		session.persist(member);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
//		Member member = new Member();
//		member.setId(id);
		Member member = session.load(Member.class,id);
		session.remove(member);
		return 1;
	}

	@Override
	public int update(Member member) {
		
		final StringBuilder hql = new StringBuilder().append("update Member set ");
		final String password = member.getPassword();
		if (password != null && !password.isEmpty()) {
			hql.append("password = :password,");
		}
		hql.append("nickname = :nickname,")
		.append("PASS = :pass,")
		.append("roleId = :roleId,")
		.append("updater = :updater,")
		.append("LAST_UPDATED_DATE = NOW() ")
		.append("where username = :username");
		
		Query<?> query = session.createQuery(hql.toString())
		.setParameter("nickname", member.getNickname())
		.setParameter("pass", member.getPass())
		.setParameter("roleId", member.getRoleId())
		.setParameter("updater", member.getUpdater())
		.setParameter("username", member.getUsername());		
		if(password != null && !password.isEmpty()) {
			query.setParameter("password", password);
		}
			return query.executeUpdate();
	}

	@Override
	public Member selectById(Integer id) {
		return session.get(Member.class,id);
	}

	@Override
	public List<Member> selectAll() {
		final String hql = "From Member order by id";
		return session.createQuery(hql,Member.class).getResultList();	
	}

	@Override
	public Member selectByUsername(String username) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
//			from Member
			Root<Member> root = criteriaQuery.from(Member.class);
//			where usename=
			criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
			
			return session.createQuery(criteriaQuery).uniqueResult();	
			}

	@Override
	public Member selectForLogin(String username, String password) {
		final String sql = "select * From java_framework.MEMBER where USERNAME = :username and PASSWORD = :password";
		NativeQuery<Member> query = session.createNativeQuery(sql, Member.class);
		
		return query.setParameter("username", username)
		.setParameter("password", password)
		.uniqueResult();
		//拿到member型態物件
	}
}
