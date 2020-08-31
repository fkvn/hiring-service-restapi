package hiringProcess.model.search;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.core.User;

@Entity
@Table(name = "logs")
public class Log implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Date timestamp;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User recorder;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Search search;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Date getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(Date timestamp) {

		this.timestamp = timestamp;
	}

	public User getRecorder() {

		return recorder;
	}

	public void setRecorder(User recorder) {

		this.recorder = recorder;
	}

	public Search getSearch() {

		return search;
	}

	public void setSearch(Search search) {

		this.search = search;
	}

}
