package hiringProcess.model.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.document.Document;
import hiringProcess.model.search.Application;
import hiringProcess.model.search.Evaluation;
import hiringProcess.model.search.Log;
import hiringProcess.model.search.RefCheckReport;
import hiringProcess.model.search.Search;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "first_name")
	@NotNull(message = "firstName cannot be null")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	@NotNull(message = "lastName cannot be null")
	private String lastName;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "Password must be Not Null")
	private String password;

	@Column(unique = true)
	@NotNull(message = "username cannot be null")
	private String username;

	@Column(unique = true)
	private String email;

	private String phone;

	@Column(columnDefinition = "boolean default false")
	private boolean isStaff;

	@Column(columnDefinition = "boolean default false")
	private boolean isAdmin;

	@Column(columnDefinition = "boolean default false")
	private boolean isSuperAdmin;

	@Column(nullable = false)
	@JsonProperty("active")
	private boolean enabled = true;

	// Relationships

	@ManyToOne
	@JoinColumn(nullable = true)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Department dept;

	@OneToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty(value = "chairDept")
	private Department chairDept;

	@ManyToMany
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<Search> searches;
	

	@OneToMany(mappedBy = "chairSearch", fetch = FetchType.LAZY)
	private List<Search> chairSearches;
	
	@OneToMany(mappedBy = "recorder", fetch = FetchType.LAZY)
	private List<Log> logs;
	
	@OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY)
	@OrderBy("appliedDate")
	private List<Application> applications;
	
	@OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY)
	@OrderBy("timestamp")
	private List<RefCheckReport> refCheckReport;
	
	@OneToMany(mappedBy = "creator",fetch = FetchType.LAZY)
	private List<Document> createdDocs;
	
	
	@OneToMany(mappedBy = "evaluator",fetch = FetchType.LAZY)
	private List<Evaluation> evaluations;


	public Long getId() {

		return id;
	}


	public void setId(Long id) {

		this.id = id;
	}


	public String getFirstName() {

		return firstName;
	}


	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}


	public String getLastName() {

		return lastName;
	}


	public void setLastName(String lastName) {

		this.lastName = lastName;
	}


	public String getMiddleName() {

		return middleName;
	}


	public void setMiddleName(String middleName) {

		this.middleName = middleName;
	}


	public String getPassword() {

		return password;
	}


	public void setPassword(String password) {

		this.password = password;
	}


	public String getUsername() {

		return username;
	}


	public void setUsername(String username) {

		this.username = username;
	}


	public String getEmail() {

		return email;
	}


	public void setEmail(String email) {

		this.email = email;
	}


	public String getPhone() {

		return phone;
	}


	public void setPhone(String phone) {

		this.phone = phone;
	}


	public boolean isEnabled() {

		return enabled;
	}


	public void setEnabled(boolean enabled) {

		this.enabled = enabled;
	}


	public List<Document> getCreatedDocs() {

		return createdDocs;
	}


	public void setCreatedDocs(List<Document> createdDocs) {

		this.createdDocs = createdDocs;
	}


	public List<RefCheckReport> getRefCheckReport() {

		return refCheckReport;
	}


	public void setRefCheckReport(List<RefCheckReport> refCheckReport) {

		this.refCheckReport = refCheckReport;
	}


	public List<Application> getApplications() {

		return applications;
	}


	public void setApplications(List<Application> applications) {

		this.applications = applications;
	}


	public List<Log> getLogs() {

		return logs;
	}


	public void setLogs(List<Log> logs) {

		this.logs = logs;
	}


	public List<Search> getSearches() {

		return searches;
	}


	public void setSearches(List<Search> searches) {

		this.searches = searches;
	}


	public Department getChairDept() {

		return chairDept;
	}


	public void setChairDept(Department chairDept) {

		this.chairDept = chairDept;
	}


	public Department getDept() {

		return dept;
	}


	public void setDept(Department dept) {

		this.dept = dept;
	}


	public boolean isSuperAdmin() {

		return isSuperAdmin;
	}


	public void setSuperAdmin(boolean isSuperAdmin) {

		this.isSuperAdmin = isSuperAdmin;
	}


	public boolean isAdmin() {

		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {

		this.isAdmin = isAdmin;
	}


	public boolean isStaff() {

		return isStaff;
	}


	public void setStaff(boolean isStaff) {

		this.isStaff = isStaff;
	}



}
