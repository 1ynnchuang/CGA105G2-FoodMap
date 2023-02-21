package tw.idv.anthony.web.emp.entity;

import tw.idv.anthony.core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emp extends Core {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Timestamp hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno;
	
	@ManyToOne
	@JoinColumn(name="deptno", insertable = false,updatable = false)
	private Dept dept;
}
