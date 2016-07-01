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
}
