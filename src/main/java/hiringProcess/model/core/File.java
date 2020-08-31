package hiringProcess.model.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "files")
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	private String name;

	@JsonIgnore
	private String type;

	@JsonValue
	private String url;
	
	@JsonIgnore
	private Long s1ize;

	@JsonIgnore
	private Date timestamp;

	@JsonIgnore
	@Lob
	private byte[] fileData;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(nullable = false)
	private User uploader;

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

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public byte[] getFileData() {

		return fileData;
	}

	public void setFileData(byte[] fileData) {

		this.fileData = fileData;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public Date getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(Date timestamp) {

		this.timestamp = timestamp;
	}

	public User getUploader() {

		return uploader;
	}

	public void setUploader(User uploader) {

		this.uploader = uploader;
	}

	public Long getS1ize() {

		return s1ize;
	}

	public void setS1ize(Long s1ize) {

		this.s1ize = s1ize;
	}



}
