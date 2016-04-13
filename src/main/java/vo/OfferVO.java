package vo;

import java.util.Date;

public class OfferVO {

	private Integer organizationId;
	private Date createdAt;
	private String description;
	private boolean disabled;
	private String duration;
	private String email;
	private String endDate;
	private boolean hasIncome;
	private boolean hasLocomotion;
	private boolean hasLunch;
	private Integer id;
	private Integer income;
	private Integer locomotion;
	private Integer lunch;
	private String post;
	private Integer quota;
	private String requirements;
	private String startDateAvailable;
	private String startDateInternship;
	private String area;
	private boolean available;
	private String type;
	private String location;
	private String[] careers;

	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public boolean isHasIncome() {
		return hasIncome;
	}
	public void setHasIncome(boolean hasIncome) {
		this.hasIncome = hasIncome;
	}
	public boolean isHasLocomotion() {
		return hasLocomotion;
	}
	public void setHasLocomotion(boolean hasLocomotion) {
		this.hasLocomotion = hasLocomotion;
	}
	public boolean isHasLunch() {
		return hasLunch;
	}
	public void setHasLunch(boolean hasLunch) {
		this.hasLunch = hasLunch;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getLocomotion() {
		return locomotion;
	}
	public void setLocomotion(Integer locomotion) {
		this.locomotion = locomotion;
	}
	public Integer getLunch() {
		return lunch;
	}
	public void setLunch(Integer lunch) {
		this.lunch = lunch;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getStartDateAvailable() {
		return startDateAvailable;
	}
	public void setStartDateAvailable(String startDateAvailable) {
		this.startDateAvailable = startDateAvailable;
	}
	public String getStartDateInternship() {
		return startDateInternship;
	}
	public void setStartDateInternship(String startDateInternship) {
		this.startDateInternship = startDateInternship;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String[] getCareers() {
		return careers;
	}
	public void setCareers(String[] careers) {
		this.careers = careers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
