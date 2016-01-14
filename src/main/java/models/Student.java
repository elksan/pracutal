package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Student {
	
	private String address;
	private Date birthdate;
	private String city;
	private String communeId;
	private Date createdAt;
	private Integer entryYear;
	private Integer rut;
	private Integer phoneNumber;
	private Integer internshipsApproved;
	private Integer priorityScore;
	private Integer regionId;
	private String citizenship;
	private String gender;
	private Date updateAt;	
	private String jobObjective;
	private Integer registrationNumber;
	
	private User user;
	private Career career;
	

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="commune_id")
	public String getCommuneId() {
		return communeId;
	}
	public void setCommuneId(String communeId) {
		this.communeId = communeId;
	}
	
	@Column(name="created_at")
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
	
	@Id
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
	
	@Column(name="region_id")
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
	@Column(name="updated_at")
	public Date getUpdateAt() {
		return updateAt;
	}	
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
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
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="career_id", unique=true, nullable=false)
	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}
}
