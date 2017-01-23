package models;

import vo.StudentVO;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedEntityGraphs({

		@NamedEntityGraph(name = "studentWithCareer",
				attributeNodes = {
						@NamedAttributeNode(value = "career")
				}
		),
		@NamedEntityGraph(name = "studentWithAddressAndCareer",
				attributeNodes = {
						@NamedAttributeNode(value = "address"),
						@NamedAttributeNode(value = "career")
				}
		)
})

public class Student extends User {
	private Date birthdate;
	private Integer entryYear;
	private Integer rut;
	private Integer phoneNumber;
	private Integer internshipsApproved;
	private Integer priorityScore;
	private String gender;
	private String jobObjective;
	private Integer registrationNumber;
	private String lastName;
	private String motherLastName;
	private Career career;
	private String curriculum;
	private List<Application> applications;
	private List<Internship> internships;

	public Student(){
		this.internshipsApproved = 0;
		this.priorityScore = 0;
	}

	public Student(StudentVO studentVO) {

		this.entryYear = Integer.parseInt(studentVO.getRegistrationNumber().toString().substring(0,4));
		this.phoneNumber = studentVO.getPhoneNumber();
		this.rut = studentVO.getRut();
		this.internshipsApproved = 0;
		this.priorityScore = 0;
		this.gender = studentVO.getGender();
		this.registrationNumber = studentVO.getRegistrationNumber();
		this.name = studentVO.getName();
		this.lastName = studentVO.getLastName();
		this.motherLastName = studentVO.getMotherLastName();
		this.email = studentVO.getEmail();
		this.curriculum = studentVO.getCurriculum();
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="mother_last_name")
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
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

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
}
