package hiringProcess.model.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.search.Search;

/**
 * Project description.
 * @author kevinngo
 */

@Entity
@Table(name = "department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String name;
	
	@OneToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User chair;
	
	@JsonIgnore
	@OneToMany(mappedBy="dept" )
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private List<User> staff;
	
	@JsonIgnore
	@OneToMany(mappedBy="dept", fetch = FetchType.LAZY)
	@OrderBy("startDate")
	private List<Search> searches;

	
	// helper methods
	
	public void addStaff(User user) {
		this.staff.add(user);
		user.setDept(this);
		user.setStaff(true);
	}
	
	// getters - setters 
	
	
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

	public List<User> getStaff() {

		return staff;
	}

	public void setStaff(List<User> staff) {

		this.staff = staff;
	}

	public List<Search> getSearches() {

		return searches;
	}

	public void setSearches(List<Search> searches) {

		this.searches = searches;
	}

	public User getChair() {

		return chair;
	}

	public void setChair(User chair) {

		this.chair = chair;
	}
	


}
