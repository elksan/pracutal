package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by maguirre on 4/13/16.
 */
@Entity
@Table(name = "offer_type")
public class OfferType {


    private Integer id;
    private String description;
    private String name;
    private List<Offer> offers;

    public OfferType() {
    }

    @Id
    @Column(name="id_offer_type", columnDefinition = "serial")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="name_type")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch= FetchType.LAZY, mappedBy="offerType")
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "OfferType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
