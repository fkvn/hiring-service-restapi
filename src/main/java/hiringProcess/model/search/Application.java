package hiringProcess.model.search;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.core.File;
import hiringProcess.model.core.User;

@Entity
@Table(name = "applications")
// @JsonPropertyOrder({"id", "status", "applicant id", "position id", "submit date", "references"})
public class Application implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private Date appliedDate;

	private String status;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("applicant")
	private User applicant;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("position")
	private Search search;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<File> attachments;

	@ManyToMany
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Reference> references;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public User getApplicant() {

		return applicant;
	}

	public void setApplicant(User applicant) {

		this.applicant = applicant;
	}

	public Search getSearch() {

		return search;
	}

	public void setSearch(Search search) {

		this.search = search;
	}

	public List<Reference> getReferences() {

		return references;
	}

	public void setReferences(List<Reference> references) {

		this.references = references;
	}

	

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public Date getAppliedDate() {

		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {

		this.appliedDate = appliedDate;
	}

	public List<File> getAttachments() {

		return attachments;
	}

	public void setAttachments(List<File> attachments) {

		this.attachments = attachments;
	}



}
