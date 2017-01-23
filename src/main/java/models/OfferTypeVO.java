package models;

import java.util.List;

/**
 * Created by Diego on 10-09-2016.
 */
public class OfferTypeVO {

	private Integer id;
	private String description;
	private String name;
	private Integer offersAvailable;

	public OfferTypeVO(OfferType offerType){
		this.id = offerType.getId();
		this.description = offerType.getDescription();
		this.name = offerType.getName();
		this.offersAvailable = 0;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOffersAvailable() {
		return offersAvailable;
	}

	public void setOffersAvailable(Integer offersAvailable) {
		this.offersAvailable = offersAvailable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
