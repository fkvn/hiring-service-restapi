package hiringProcess.model.search;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.core.Department;
import hiringProcess.model.core.User;

@Entity
@Table(name = "searches")
public class Search implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Search name cannot be null")
	private String name;

	@NotNull(message = "Search description cannot be null")
	private String description;

	@Column(nullable = false)
	@NotNull(message = "Search salary cannot be null")
	private double salary;

	private Date startDate = new Date();

	private Date endDate;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Department dept;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> committees;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty(value = "Chair")
	private User chairSearch;

	@OneToMany(mappedBy = "search", fetch = FetchType.LAZY)
	private List<Log> logs;

	@OneToMany(mappedBy = "search", fetch = FetchType.LAZY)
	private List<Application> applications;

	public Department getDept() {

		return dept;
	}

	public void setDept(Department dept) {

		this.dept = dept;
	}

	public List<User> getCommittees() {

		return committees;
	}

	public void setCommittees(List<User> committees) {

		this.committees = committees;
	}

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

	public double getSalary() {

		return salary;
	}

	public void setSalary(double salary) {

		this.salary = salary;
	}

	public Date getStartDate() {

		return startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {

		return endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public List<Log> getLogs() {

		return logs;
	}

	public void setLogs(List<Log> logs) {

		this.logs = logs;
	}

	public User getChairSearch() {

		return chairSearch;
	}

	public void setChairSearch(User chairSearch) {

		this.chairSearch = chairSearch;
	}

	public List<Application> getApplications() {

		return applications;
	}

	public void setApplications(List<Application> applications) {

		this.applications = applications;
	}

}
