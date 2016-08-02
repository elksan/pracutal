package models;

/**
 * Created by Diego on 22-04-2016.
 */

import vo.OrganizationVO;
import vo.VerificationToken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Organization extends User{

	private String activity;
	private String area;
	private String description;
	private String webpage;
	private Integer phoneNumber;
	private List<Offer> offers;
	private List<Internship> internships;

	public Organization() {
	}

	public Organization(OrganizationVO organizationVO) {

		this.activity = organizationVO.getActivity();
		this.area = organizationVO.getArea();
		this.description = organizationVO.getDescription();
		this.webpage = organizationVO.getWebpage();
		this.phoneNumber = organizationVO.getPhoneNumber();
		this.name = organizationVO.getName();
		this.email = organizationVO.getEmail().toLowerCase();
		this.roles = new ArrayList<>();
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	@Column(name="phone_number")
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		disabled = true;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	public List<Internship> getInternships() {
		return internships;
	}

	public void setInternships(List<Internship> internships) {
		this.internships = internships;
	}
}
