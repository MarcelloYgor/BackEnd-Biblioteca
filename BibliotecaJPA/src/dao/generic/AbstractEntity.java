package dao.generic;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Transient public static final long serialVersionUID = 196919661993L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
}
