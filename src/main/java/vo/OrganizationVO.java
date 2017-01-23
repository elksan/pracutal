package vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.Offer;
import models.Organization;
import models.Role;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by Diego on 12-06-2016.
 */
public class OrganizationVO extends UserVO{

	private String activity;
	private String area;
	private Boolean disabled;
	private String description;
	private String webpage;
	private Integer phoneNumber;
	private String streetName;
	private Integer streetNumber;
	private Integer apartmentNumber;
	private String commune;
	private String city;
	private String profilePhotoPath;

	public OrganizationVO(){

	}
	public OrganizationVO(Organization organization){

		activity = organization.getActivity();
		area = organization.getArea();
		disabled = organization.getDisabled();
		description = organization.getDescription();
		webpage = organization.getWebpage();
		phoneNumber = organization.getPhoneNumber();
		id = organization.getId();
		name = organization.getName();
		this. profilePhotoPath = organization.getProfilePhotoPath();
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
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

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}
}
