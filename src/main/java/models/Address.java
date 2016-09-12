package models;

import vo.OrganizationVO;

import javax.persistence.*;

/**
 * Created by Diego on 10-09-2016.
 */
@Entity
public class Address {

	private Integer id;
	private String streetName;
	private Integer streetNumber;
	private Integer apartmentNumber;
	private String commune;
	private String city;

	private User user;

	public Address(){
	}

	public Address(OrganizationVO organizationVO){
		this.streetName = organizationVO.getStreetName();
		this.streetNumber = organizationVO.getStreetNumber();
		this.apartmentNumber = organizationVO.getApartmentNumber();
		this.commune = organizationVO.getCommune();
		this.city = organizationVO.getCity();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "street_name")
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	@Column(name = "street_number")
	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}
	@Column(name = "apartment_number")
	public Integer getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(Integer apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
/*
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}*/
}
