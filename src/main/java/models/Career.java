package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Career {

	private Integer careerId;
	private String careerName;
	private Student student;
	
	@Id
	@Column(name="career_id")
	public Integer getCareerId() {
		return careerId;
	}
	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}
	
	@Column(name="career_name")
	public String getCareerName() {
		return careerName;
	}
	public void setCareerName(String careerName) {
		this.careerName = careerName;
	}
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="career")
	@PrimaryKeyJoinColumn
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
