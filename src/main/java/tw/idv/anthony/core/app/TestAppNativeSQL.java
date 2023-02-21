package tw.idv.anthony.core.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import tw.idv.anthony.core.util.HibernateUtil;
import tw.idv.anthony.web.member.entity.Member;

import java.util.List;

public class TestAppNativeSQL {

	public static void main(String[] args) {
		String sql = "select e.* " 
				+ "from EMP e\n" 
				+ "join (select distinct YEAR(HIREDATE) as year\n" 
				+ "        from\n" 
				+ "            EMP\n" 
				+ "        order by\n"
				+ "            YEAR(HIREDATE)\n" 
				+ "        limit\n" 
				+ "            2) t1\n"
				+ "    on YEAR(e.HIREDATE) = t1.YEAR\n" 
				+ "order by e.HIREDATE;";

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		NativeQuery<Member> nativeQuery = session.createNativeQuery(sql, Member.class);
		List<Member> list = nativeQuery.list();
	}

	public static Integer insert(Member member) {

//		啟動hibernate功能
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
//		建立交易物件
			Transaction tx = session.beginTransaction();

//		將實體物件新增⾄對應資料表中
			session.persist(member);
			tx.commit();

//		取得自動產生的編號
			return member.getId();
		} catch (Exception e) {
//			取得當前連線
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		}
	}

	public String deleteById(Integer id) {

//		啟動hibernate功能
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
//		建立交易物件
			Transaction tx = session.beginTransaction();
			Member member = new Member();
			member.setId(id);
//		
			session.remove(member);
			tx.commit();

			String s = "OK";
			return s;

		} catch (Exception e) {
//			取得當前連線
			session.getTransaction().rollback();
			e.printStackTrace();
			return "0";
		}
	}

	public static void updateByID(Member newMember) {

//	啟動hibernate功能
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
//	建立交易物件
			Transaction tx = session.beginTransaction();

//			找出要更改的物件
			Member oldMember = session.get(Member.class, newMember.getId());

			final String password = newMember.getPassword();
			if (password != null && !password.isEmpty()) {
				oldMember.setPassword(password);
			}

			final String nickname = newMember.getNickname();
			if (nickname != null && !nickname.isEmpty()) {
				oldMember.setPassword(nickname);
			}

			tx.commit();

//	取得自動產生的編號
		} catch (Exception e) {
//		取得當前連線
			session.getTransaction().rollback();
			e.printStackTrace();

		}
	}

	public static Member selectByID(Integer id) {

//		啟動hibernate功能
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
//		建立交易物件
			Transaction tx = session.beginTransaction();

			Member member = session.get(Member.class, id);

			tx.commit();

			return member;
		} catch (Exception e) {
//			取得當前連線
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		}
	}
}
