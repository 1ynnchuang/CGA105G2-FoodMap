package tw.idv.anthony.core.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tw.idv.anthony.core.util.HibernateUtil;
import tw.idv.anthony.web.member.entity.Member;


public class TestAppHql {

	private static final String String = null;

	public static void main(String[] args) {
////		啟動hibernate功能
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//
////		拿session型態的物件
//		Session session = sessionFactory.openSession();
//		Member member = session.get(Member.class, 1);
//
//		System.out.println(member.getNickname());
//
////		關閉hibernate功能
//		HibernateUtil.shutdown();

////		insert東西
//		TestAppHql app = new TestAppHql();
//		Member member = new Member();
//		member.setUsername("abc0000");
//		member.setPassword("0000");
//		member.setNickname("abc");
////		member.setPass(true);
////		member.setRoleId(1);
////		member.setCreator("creater");
//
////		資料庫有defuault也不行值接省略這行,要使用annotation在VO中跟他說新增時忽略此field
////		member.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//
//		Integer id = app.insert(member);
//		System.out.println(id);

////		delete
//		TestAppHql app = new TestAppHql();
//		System.out.println(app.deleteById(6));

//		update
		TestAppHql app = new TestAppHql();
		Member member = new Member();
//		member.setId(1);
		member.setNickname("www");
		member.setUsername("William");;
//		member.setPassword("12345678");
		app.updateByID(member);

////		select
//		TestAppHql app = new TestAppHql();
//		System.out.println(app.selectByID(2).getNickname());

	}

	public static Integer insert(Member member) {

//		啟動hibernate功能
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
//		建立交易物件
			Transaction tx = session.beginTransaction();
			String hql = "INSERT INTO Member(username,password,nickname) " + "SELECT :username , :password, :nickname "
					+ "FROM Member " + "WHERE id = 1";

//			方法鏈
			session.createQuery(hql)
			.setParameter("username", member.getUsername())
			.setParameter("password", member.getPassword())
			.setParameter("nickname", member.getNickname())
			.executeUpdate();
			tx.commit();

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
			String hql = "DELETE Member " + "WHERE id = :id";
			session.createQuery(hql).setParameter("id", id).executeUpdate();// 執行的意思

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

	public void updateByID(Member newMember) {

//	啟動hibernate功能
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
//	建立交易物件
			Transaction tx = session.beginTransaction();
			String hql = "UPDATE Member " + "SET nickname = :nickname " + "WHERE username = :username";
			session.createQuery(hql)
					.setParameter("nickname", newMember.getNickname())
					.setParameter("username", newMember.getUsername())
					.executeUpdate();

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

			String hql = "FROM Member WERE id = :id";
			Member member = session.createQuery(hql, Member.class)
					.setParameter("id", id).getSingleResult();

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
