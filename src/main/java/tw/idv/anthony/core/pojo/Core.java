package tw.idv.anthony.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Core implements Serializable {
	private static final long serialVersionUID = 1457755989409740329L;
	private boolean successful;
	private String message;
}
