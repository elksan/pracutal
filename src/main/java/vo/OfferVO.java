package vo;

import models.Career;
import models.Offer;
import models.OfferType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferVO {

    Logger logger = LoggerFactory.getLogger(OfferVO.class);

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
	private Integer offerTypeId;
	private OfferType offerType;
	private String location;
	private List<Integer> careers;
	private String title;


	public OfferVO() {
	}

	public OfferVO(Offer offer) {

		this.organizationId = offer.getOrganization().getId();
		this.createdAt = offer.getCreatedAt();
		this.description = offer.getDescription();
		this.disabled = offer.isDisabled();
		this.duration = offer.getDuration();
		this.email = offer.getEmail();
		this.endDate = isValid(offer.getEndDate()) ? parseDate(offer.getEndDate()) : "";
		this.hasIncome = offer.isHasIncome();
		this.hasLocomotion = offer.isHasLocomotion();
		this.hasLunch = offer.isHasLunch();
		this.id = offer.getId();
		this.income = offer.getIncome();
		this.locomotion = offer.getLocomotion();
		this.lunch = offer.getLunch();
		this.post = offer.getPost();
		this.quota = offer.getQuota();
		this.requirements = offer.getRequirements();
		this.startDateAvailable = isValid(offer.getStartDateAvailable()) ? parseDate(offer.getStartDateAvailable()) : "";
		this.startDateInternship = isValid(offer.getStartDateInternship()) ? parseDate(offer.getStartDateInternship()) : "";
		this.area = offer.getArea();
		this.available = offer.isAvailable();
		this.offerType = offer.getOfferType();
		this.offerTypeId = offer.getOfferType().getId();
		this.location = offer.getLocation();
		this.careers = getCarrersIds(offer.getCareers());
		this.title = offer.getTitle();
	}

	public String parseDate(Date fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("d MMM, yyyy");
			return sdf.format(fecha);
		}
		catch (Exception e){
			logger.debug("ERROR AL PARSEAR LA FECHA!");
			return "";
		}
	}

	public boolean isValid(Date fecha){

		if(fecha != null && !"".equals(fecha)) return true;
		return true;
	}

	private List<Integer> getCarrersIds(List<Career> careers){
		List<Integer> careersIds = new ArrayList<>();

		for(Career career : careers){
			careersIds.add(career.getCareerId());
		}
		return careersIds;
	}


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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getCareers() {
		return careers;
	}

	public void setCareers(List<Integer> careers) {
		this.careers = careers;
	}

	public Integer getOfferTypeId() {
		return offerTypeId;
	}

	public void setOfferTypeId(Integer offerTypeId) {
		this.offerTypeId = offerTypeId;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}
}
