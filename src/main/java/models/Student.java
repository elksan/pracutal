package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Student extends User {


	private Date birthdate;
	private Date createdAt;
	private Integer entryYear;
	private Integer rut;
	private Integer phoneNumber;
	private Integer internshipsApproved;
	private Integer priorityScore;
	private String gender;
	private Date updateAt;	
	private String jobObjective;
	private Integer registrationNumber;

	private Career career;
	private List<Application> applications;
	private List<Internship> internships;

	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name="created_at", insertable=false, updatable=false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name="entry_year")
	public Integer getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(Integer entryYear) {
		this.entryYear = entryYear;
	}

	@Column(name="rut")
	public Integer getRut() {
		return rut;
	}
	public void setRut(Integer rut) {
		this.rut = rut;
	}
	
	@Column(name="phone_number")
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="internships_approved")
	public Integer getInternshipsApproved() {
		return internshipsApproved;
	}
	public void setInternshipsApproved(Integer internshipsApproved) {
		this.internshipsApproved = internshipsApproved;
	}
	
	@Column(name="priority_score")
	public Integer getPriorityScore() {
		return priorityScore;
	}
	public void setPriorityScore(Integer priorityScore) {
		this.priorityScore = priorityScore;
	}

	@Column(name="updated_at", insertable=false, updatable=false)
	public Date getUpdateAt() {
		return updateAt;
	}	
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name="job_objective")
	public String getJobObjective() {
		return jobObjective;
	}
	public void setJobObjective(String jobObjective) {
		this.jobObjective = jobObjective;
	}

	@Column(name="registration_number")
	public Integer getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="career_id", unique=true, nullable=false)
	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public List<Internship> getInternships() {
		return internships;
	}

	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}
}
