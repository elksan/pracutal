package vo;

import models.Application;
import models.Career;
import models.Internship;

import java.util.Date;
import java.util.List;

/**
 * Created by Diego on 27-08-2016.
 */
public class StudentVO extends UserVO{

	private String birthDate;
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
	private Integer careerId;
	private String curriculum;

	private String streetName;
	private Integer streetNumber;
	private Integer apartmentNumber;
	private String commune;
	private String city;

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(Integer entryYear) {
		this.entryYear = entryYear;
	}

	public Integer getRut() {
		return rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getInternshipsApproved() {
		return internshipsApproved;
	}

	public void setInternshipsApproved(Integer internshipsApproved) {
		this.internshipsApproved = internshipsApproved;
	}

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

	public String getJobObjective() {
		return jobObjective;
	}

	public void setJobObjective(String jobObjective) {
		this.jobObjective = jobObjective;
	}

	public Integer getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	public Integer getCareerId() {
		return careerId;
	}

	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

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

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
}
