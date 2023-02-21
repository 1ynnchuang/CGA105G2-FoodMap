package tw.idv.anthony.web.emp.entity;

import tw.idv.anthony.core.pojo.Core;
import tw.idv.anthony.core.util.HibernateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dept extends Core {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer deptno;
	private String dname;
	private String loc;
	@OneToMany(mappedBy = "dept")
	private List<Emp> emps;

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Emp emp = session.get(Emp.class, 7369);
		System.out.println(emp.getDept().getDname());
		System.out.println("===================");
		Dept dept = emp.getDept();
		System.out.println(dept.getDname());
		System.out.println("===================");
		for(Emp e : dept.getEmps()) {
			System.out.println(e.getEname());
		}
	}
}
