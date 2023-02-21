package tw.idv.anthony.web.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import tw.idv.anthony.core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Core {
	private static final long serialVersionUID = 1062017833925367218L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String nickname;

	@Column(insertable = false)
	private Boolean pass;

	@Column(name = "ROLE_ID")
	private Integer roleId;

	@Column(insertable = false)
	private String creator;

//	@Column(name = "CREATED_DATE", insertable = false)
	@Column(insertable = false)
	private Timestamp createdDate;

//	@Column(insertable = false)
	private String updater;

//	@Column(name = "LAST_UPDATED_DATE", insertable = false)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp lastUpdatedDate;

//	@Column
	private byte[] image;
}
