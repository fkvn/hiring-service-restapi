package hiringProcess.model.document;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hiringProcess.model.core.File;

@Entity
@Table(name = "revisions")
public class Revision implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String notes;
	
	private Date createdDate;


	@OneToOne
	private File file;

	@JsonIgnore
	@ManyToOne
	private Document document;

	public String getNotes() {

		return notes;
	}

	public void setNotes(String notes) {

		this.notes = notes;
	}

	public File getFile() {

		return file;
	}

	public void setFile(File file) {

		this.file = file;
	}

	public Document getDocument() {

		return document;
	}

	public void setDocument(Document document) {

		this.document = document;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Date getCreatedDate() {

		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

}
