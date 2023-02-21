package tw.idv.anthony.core.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tw.idv.anthony.core.util.HibernateUtil;
import tw.idv.anthony.web.member.dao.MemberDao;
import tw.idv.anthony.web.member.entity.Member;


public class TestApp {

	private static final String String = null;

	public static void main(String[] args) {
//	Hibernate
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Member member = session.get(Member.class, 1);
//		System.out.println(member.getNickname());
//		HibernateUtil.shutdown();
		//		注入
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		取出
//		DL(LookUp)，因為還是要主動去叫他。
		MemberDao memberDao=applicationContext.getBean(MemberDao.class);
		Member member=memberDao.selectById(1);
		System.out.println(member.getNickname());

//		關閉
		((ConfigurableApplicationContext) applicationContext).close();


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
//		Member member = new Member();
//		member.setUsername("Lynn");
//		member.setPassword("0000");
//		member.setNickname("nickname");
//		member.setPass(true);
//		member.setRoleId(1);
//		member.setCreator("creater");
//
////		資料庫有defuault也不行值接省略這行,要使用annotation在VO中跟他說新增時忽略此field
//		member.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//
//		TestApp app = new TestApp();
//		Integer id = app.insert(member);
//		System.out.println(id);

////		delete
//		TestApp app = new TestApp();
//		System.out.println(app.deleteById(4));

////		update
//		TestApp app = new TestApp();
//		Member member = new Member();
//		member.setId(5);
//		member.setNickname("暱稱");
//		member.setPassword("密碼");
//		app.updateByID(member);
		
		
//		select
		TestApp app = new TestApp();
		System.out.println(app.selectByID(2).getNickname());
		
		

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
