package hiringProcess.model.document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.core.User;

@Entity
@Table(name = "documents")
@JsonPropertyOrder({ "id", "name", "creator", "updated", "revisions" })
public class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "document")
	@OrderBy("createdDate")
	private List<Revision> revisions;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty(value = "creator")
	private User creator;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Integer revisions() {

		return getRevisions().size();
	}

	public Date updated() {

		return getRevisions().get(getRevisions().size() - 1).getCreatedDate();
	}

	public List<Revision> getRevisions() {

		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {

		this.revisions = revisions;
	}

	public User getCreator() {

		return creator;
	}

	public void setCreator(User creator) {

		this.creator = creator;
	}

}

