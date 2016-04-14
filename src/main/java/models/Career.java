package models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Career {

	private Integer careerId;
	private String careerName;
	private List<Student> students;
	private List<Offer> offers;
	
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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="career")
	@PrimaryKeyJoinColumn
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "careers")
	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
}
