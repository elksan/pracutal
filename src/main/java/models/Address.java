package models;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import vo.OrganizationVO;
import vo.StudentVO;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

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

	public Address(StudentVO studentVO){
		this.streetName = studentVO.getStreetName();
		this.streetNumber = studentVO.getStreetNumber();
		this.apartmentNumber = studentVO.getApartmentNumber();
		this.commune = studentVO.getCommune();
		this.city = studentVO.getCity();
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "user"))
	@GeneratedValue(generator = "generator")
	@Column( unique = true, nullable = false)
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

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@PrimaryKeyJoinColumn
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
