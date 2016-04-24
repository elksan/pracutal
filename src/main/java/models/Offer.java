package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import vo.OfferVO;

@Entity
@Table(name = "offer")
public class Offer implements Serializable {

	//private Integer organizationId;
	private Date createdAt;
	private Date updatedAt;
	private String description;
	private boolean disabled;
	private String duration;
	private String email;
	private Date endDate;
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
	private Date startDateAvailable;
	private Date startDateInternship;
	private String area;
	private boolean available;
	private String location;
	private String title;
	private OfferType offerType;
	private List<Career> careers;
	private Organization organization;
	
	public Offer() {
		
	}
	
	public Offer(OfferVO offerVO) {
		super();
		//this.organizationId = offerVO.getOrganizationId();
		this.createdAt = offerVO.getCreatedAt();
		this.description = offerVO.getDescription();
		this.disabled = offerVO.isDisabled();
		this.duration = offerVO.getDuration();
		this.email = offerVO.getEmail();
		this.hasIncome = offerVO.isHasIncome();
		this.hasLocomotion = offerVO.isHasLocomotion();
		this.hasLunch = offerVO.isHasLunch();
		this.id = offerVO.getId();
		this.income = offerVO.getIncome();
		this.locomotion = offerVO.getLocomotion();
		this.lunch = offerVO.getLunch();
		this.post = offerVO.getPost();
		this.quota = offerVO.getQuota();
		this.requirements = offerVO.getRequirements();
		this.area = offerVO.getArea();
		this.available = offerVO.isAvailable();
		this.location = offerVO.getLocation();
		this.title = offerVO.getTitle();
	}
	/*@Column(name="organization_id")
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}*/
	
	@Column(name="created_at")
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
	
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name="has_income")
	public boolean isHasIncome() {
		return hasIncome;
	}
	public void setHasIncome(boolean hasIncome) {
		this.hasIncome = hasIncome;
	}
	
	@Column(name="has_locomotion")
	public boolean isHasLocomotion() {
		return hasLocomotion;
	}
	public void setHasLocomotion(boolean hasLocomotion) {
		this.hasLocomotion = hasLocomotion;
	}
	
	@Column(name="has_lunch")
	public boolean isHasLunch() {
		return hasLunch;
	}
	public void setHasLunch(boolean hasLunch) {
		this.hasLunch = hasLunch;
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
	
	@Column(name="start_date_available")
	public Date getStartDateAvailable() {
		return startDateAvailable;
	}
	public void setStartDateAvailable(Date startDateAvailable) {
		this.startDateAvailable = startDateAvailable;
	}
	
	@Column(name="start_date_internship")
	public Date getStartDateInternship() {
		return startDateInternship;
	}
	public void setStartDateInternship(Date startDateInternship) {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="offer_type_id", unique=true, nullable=false)
	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="offer_career", joinColumns = @JoinColumn(name = "offer_id"), inverseJoinColumns = @JoinColumn(name = "career_id"))
	public List<Career> getCareers() {
		return careers;
	}

	public void setCareers(List<Career> careers) {
		this.careers = careers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		 updatedAt = new Date();
	}


}
