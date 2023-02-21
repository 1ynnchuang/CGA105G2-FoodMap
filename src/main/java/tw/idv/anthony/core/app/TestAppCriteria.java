package tw.idv.anthony.core.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tw.idv.anthony.core.util.HibernateUtil;
import tw.idv.anthony.web.member.entity.Member;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
;

public class TestAppCriteria {

	private static final String String = null;

	public static void main(String[] args) {
//		拿session型態的物件
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		取得CriteriaBuilder物件
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
//		建⽴CriteriaQuery物件
		CriteriaQuery<Member> criteriaQuery= criteriaBuilder.createQuery(Member.class);

//		取得ROOT物件
		Root<Member> root = criteriaQuery.from(Member.class);
		
//		加入where子句 where ID > 2
		criteriaQuery.where(criteriaBuilder.greaterThan(root.get("id"), 2));
		
//		group by ROLE_ID
		criteriaQuery.groupBy(root.get("roleId"));
		
//		having COUNT(*) > 1 算出來是long,所以要加L
		criteriaQuery.having(criteriaBuilder.greaterThan(criteriaBuilder.count(root), 1L));
		
//		select ROLE_ID
		criteriaQuery.select(root.get("roleId"));
		
//		order by ROLE_ID
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("roleId")));
		
		Query<Member> query = session.createQuery(criteriaQuery);
		query.list();
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
