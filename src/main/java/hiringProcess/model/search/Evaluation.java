package hiringProcess.model.search;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import hiringProcess.model.core.User;

@Entity
@Table(name = "evaluations")
public class Evaluation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String notes;

	private Date timestamp;

	@Column(columnDefinition = "boolean default false")
	private boolean MinUnderGradQuafTeaching;

	@Column(columnDefinition = "boolean default false")
	private boolean MinQuafEducation;

	@Column(columnDefinition = "boolean default false")
	private boolean AllReqMeterialSubmitted;

	@Column(columnDefinition = "boolean default false")
	private boolean GradTeachingExp;

	@Column(columnDefinition = "boolean default false")
	private boolean ExpertiseQuaf;

	@Column(columnDefinition = "boolean default false")
	private boolean AdvPhoneInterview;

	@Column(columnDefinition = "boolean default false")
	private boolean AdvMainInterview;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User evaluator;

	@OneToOne(fetch = FetchType.LAZY)
	private Application application;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getNotes() {

		return notes;
	}

	public void setNotes(String notes) {

		this.notes = notes;
	}

	public Date getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(Date timestamp) {

		this.timestamp = timestamp;
	}

	public boolean isMinUnderGradQuafTeaching() {

		return MinUnderGradQuafTeaching;
	}

	public void setMinUnderGradQuafTeaching(boolean minUnderGradQuafTeaching) {

		MinUnderGradQuafTeaching = minUnderGradQuafTeaching;
	}

	public boolean isMinQuafEducation() {

		return MinQuafEducation;
	}

	public void setMinQuafEducation(boolean minQuafEducation) {

		MinQuafEducation = minQuafEducation;
	}

	public boolean isAllReqMeterialSubmitted() {

		return AllReqMeterialSubmitted;
	}

	public void setAllReqMeterialSubmitted(boolean allReqMeterialSubmitted) {

		AllReqMeterialSubmitted = allReqMeterialSubmitted;
	}

	public boolean isGradTeachingExp() {

		return GradTeachingExp;
	}

	public void setGradTeachingExp(boolean gradTeachingExp) {

		GradTeachingExp = gradTeachingExp;
	}

	public boolean isExpertiseQuaf() {

		return ExpertiseQuaf;
	}

	public void setExpertiseQuaf(boolean expertiseQuaf) {

		ExpertiseQuaf = expertiseQuaf;
	}

	public boolean isAdvPhoneInterview() {

		return AdvPhoneInterview;
	}

	public void setAdvPhoneInterview(boolean advPhoneInterview) {

		AdvPhoneInterview = advPhoneInterview;
	}

	public boolean isAdvMainInterview() {

		return AdvMainInterview;
	}

	public void setAdvMainInterview(boolean advMainInterview) {

		AdvMainInterview = advMainInterview;
	}

	public User getEvaluator() {

		return evaluator;
	}

	public void setEvaluator(User evaluator) {

		this.evaluator = evaluator;
	}

	public Application getApplication() {

		return application;
	}

	public void setApplication(Application application) {

		this.application = application;
	}


}
